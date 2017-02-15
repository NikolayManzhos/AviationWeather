package defaultapps.com.aviationweather.activites;

import android.app.SearchManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.adapters.FavoritesAdapter;
import defaultapps.com.aviationweather.adapters.MainTabAdapter;
import defaultapps.com.aviationweather.fragments.MetarFragment;
import defaultapps.com.aviationweather.fragments.ProcessingFragment;
import defaultapps.com.aviationweather.fragments.TafFragment;
import defaultapps.com.aviationweather.miscs.MyApplication;
import defaultapps.com.aviationweather.miscs.PreferencesManager;
import defaultapps.com.aviationweather.miscs.Utils;
import defaultapps.com.aviationweather.views.MainView;


public class MainActivity extends AppCompatActivity implements MainView {

    private MainTabAdapter mainTabAdapter;
    private FavoritesAdapter favoritesAdapter;
    private MenuItem refreshItem;
    private ProcessingFragment processingFragment;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout mainTab;

    @BindView(R.id.view_pager)
    ViewPager mainPager;

    @BindView(R.id.activity_main)
    View parentView;

    @BindView(R.id.fab_favorite)
    FloatingActionButton fabFavorite;

    @BindView(R.id.navigation_drawer_recycler)
    RecyclerView recyclerView;

    private final String TAG = "MainActivity";

    private Set<String> favAirports;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, (DrawerLayout) parentView, toolbar, R.string.drawer_open, R.string.drawer_close);
        ((DrawerLayout) parentView).addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //Tabs setup
        mainPager.setAdapter(mainTabAdapter = new MainTabAdapter(getSupportFragmentManager()));
        mainTab.setupWithViewPager(mainPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        favAirports = (PreferencesManager.get().getFavoriteAirports() != null ? PreferencesManager.get().getFavoriteAirports() : new LinkedHashSet<String>());
        processingFragment = (ProcessingFragment) fragmentManager.findFragmentByTag("proc");
        if (processingFragment == null) {
            processingFragment = new ProcessingFragment();
            fragmentManager.beginTransaction().add(processingFragment, "proc").commit();
        }
        processingFragment.setMainView(this);

        //fab setup
        fabFavorite.setImageDrawable(new IconDrawable(this, MaterialIcons.md_favorite).colorRes(R.color.textColorPrimary));
        showFavoriteButton();

        //setup list of the favorite airports
        favoritesAdapter = new FavoritesAdapter(favAirports);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(favoritesAdapter);
        favoritesAdapter.setListener(new FavoritesAdapter.Listener() {
            @Override
            public void onClick(String airportCode) {
                processingFragment.submitQuery(airportCode);
                ((DrawerLayout) parentView).closeDrawers();
            }

            @Override
            public void onDeleteClick(String airportCode, int position) {
                Vibrator vibrate = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                vibrate.vibrate(10);
                favAirports.remove(airportCode);
                PreferencesManager.get().deleteFavoriteAirport(airportCode);
                Utils.showSnackbar(parentView, airportCode + "removed from favorites");
                showFavoriteButton();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        refreshItem = menu.findItem(R.id.action_refresh);
        Utils.setMenuIcon(refreshItem, MaterialIcons.md_refresh);
        if (!PreferencesManager.get().getCurrentAirCode().equals("none")) {
            showRefreshButton();
        } else {
            hideRefreshButton();
        }


        final MenuItem searchItem = menu.findItem(R.id.action_search);
        Utils.setMenuIcon(searchItem, MaterialIcons.md_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(getString(R.string.search_hint));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        processingFragment.setFragments((MetarFragment) mainTabAdapter.getFragment(0), (TafFragment) mainTabAdapter.getFragment(1));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processingFragment.submitQuery(query);
                searchView.setIconified(true);
                searchItem.collapseActionView();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void showErrorSnackbar() {
        Log.i(TAG, "SNACK");
        Utils.showSnackbar(mainPager, "Wrong airport code.");
    }

    @Override
    public void showFavoriteButton() {
//        !PreferencesManager.get().getFavoriteAirports().contains(processingFragment.getCurrentAirCode())
        if (!PreferencesManager.get().getFavoriteAirports().contains(PreferencesManager.get().getCurrentAirCode())) {
            Log.i(TAG, "FAB is visible.");
            Log.i(TAG, PreferencesManager.get().getCurrentAirCode());
            fabFavorite.setVisibility(View.VISIBLE);
            fabFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!PreferencesManager.get().getCurrentAirCode().equals("none")) {
                        PreferencesManager.get().setFavoriteAirport(processingFragment.getCurrentAirCode());
                        favAirports.add(PreferencesManager.get().getCurrentAirCode());
                    }
                    hideFavoriteButton();
                }
            });
        } else {
            hideFavoriteButton();
        }

    }

    @Override
    public void hideFavoriteButton() {
        fabFavorite.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRefreshButton() {
        if (refreshItem != null) {
            refreshItem.setVisible(true);
            refreshItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    processingFragment.submitQuery(PreferencesManager.get().getCurrentAirCode());
                    return false;
                }
            });
        }
    }

    @Override
    public void hideRefreshButton() {
        refreshItem.setVisible(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "proc", getSupportFragmentManager().findFragmentByTag("proc"));
    }

}

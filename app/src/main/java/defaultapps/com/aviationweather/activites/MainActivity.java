package defaultapps.com.aviationweather.activites;

import android.app.SearchManager;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.joanzapata.iconify.fonts.MaterialIcons;

import butterknife.BindView;
import butterknife.ButterKnife;
import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.adapters.MainTabAdapter;
import defaultapps.com.aviationweather.fragments.MetarFragment;
import defaultapps.com.aviationweather.fragments.ProcessingFragment;
import defaultapps.com.aviationweather.fragments.TafFragment;
import defaultapps.com.aviationweather.miscs.PreferencesManager;
import defaultapps.com.aviationweather.miscs.Utils;
import defaultapps.com.aviationweather.views.MainView;


public class MainActivity extends AppCompatActivity implements MainView {

    private MainTabAdapter mainTabAdapter;
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
    FloatingActionButton favFloatingActionButton;

    private final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, (DrawerLayout) parentView, toolbar, R.string.drawer_open, R.string.drawer_close);
        ((DrawerLayout) parentView).addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        mainPager.setAdapter(mainTabAdapter = new MainTabAdapter(getSupportFragmentManager()));
        mainTab.setupWithViewPager(mainPager);

        FragmentManager fragmentManager = getSupportFragmentManager();
        processingFragment = (ProcessingFragment) fragmentManager.findFragmentByTag("proc");

        if (processingFragment == null) {
            processingFragment = new ProcessingFragment();
            fragmentManager.beginTransaction().add(processingFragment, "proc").commit();
            Log.i(TAG, "processingFragment == null");
        }
        processingFragment.setMainView(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem refreshItem = menu.findItem(R.id.action_refresh);
        Utils.setMenuIcon(refreshItem, MaterialIcons.md_refresh);


        final MenuItem searchItem = menu.findItem(R.id.action_search);
        Utils.setMenuIcon(searchItem, MaterialIcons.md_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processingFragment.setFragments((MetarFragment) mainTabAdapter.getFragment(0), (TafFragment) mainTabAdapter.getFragment(1));
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

        refreshItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "proc", getSupportFragmentManager().findFragmentByTag("proc"));
    }

}

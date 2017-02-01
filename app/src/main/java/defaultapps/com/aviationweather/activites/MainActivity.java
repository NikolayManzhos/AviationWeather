package defaultapps.com.aviationweather.activites;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



import butterknife.BindView;
import butterknife.ButterKnife;
import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.adapters.MainTabAdapter;


public class MainActivity extends AppCompatActivity {

    private MainTabAdapter mainTabAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout mainTab;

    @BindView(R.id.view_pager)
    ViewPager mainPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);

        if (mainPager != null) {
            mainPager.setAdapter(mainTabAdapter = new MainTabAdapter(getSupportFragmentManager()));
            mainTab.setupWithViewPager(mainPager);
        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        getSupportFragmentManager().putFragment(outState, "forecast", getSupportFragmentManager().findFragmentByTag("forecast"));
    }
}

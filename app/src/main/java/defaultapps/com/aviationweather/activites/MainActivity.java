package defaultapps.com.aviationweather.activites;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import butterknife.BindView;
import butterknife.ButterKnife;
import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.fragments.ForecastFragment;


public class MainActivity extends AppCompatActivity {

    private ForecastFragment forecastFragment;

    @BindView(R.id.toolbar)
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            forecastFragment = (ForecastFragment) getSupportFragmentManager().getFragment(savedInstanceState, "forecast");
        }

        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        showFragment(0);
    }

    private void showFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                if (forecastFragment != null) {
                    ft.show(forecastFragment);
                } else {
                    forecastFragment = new ForecastFragment();
                    ft.add(R.id.content_frame, forecastFragment, "forecast");
                }
                break;
            default:
                break;

        }
        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "forecast", getSupportFragmentManager().findFragmentByTag("forecast"));
    }
}

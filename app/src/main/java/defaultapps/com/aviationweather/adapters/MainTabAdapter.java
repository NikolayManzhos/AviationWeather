package defaultapps.com.aviationweather.adapters;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.controllers.MetarController;
import defaultapps.com.aviationweather.fragments.MetarFragment;
import defaultapps.com.aviationweather.fragments.TafFragment;
import defaultapps.com.aviationweather.utils.MyApplication;

/**
 * Created on 2/1/2017.
 */

public class MainTabAdapter extends FragmentPagerAdapter {

    private String[] tabNames;

    public MetarFragment metarFragment;
    public TafFragment tafFragment;


    public MainTabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        tabNames = MyApplication.getAppContext().getResources().getStringArray(R.array.tab_names);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return metarFragment = new MetarFragment();
            case 1:
                return tafFragment = new TafFragment();
            default:
                return new MetarFragment();
        }
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }


    public void updateMetarTaf(String airportCode) {
        metarFragment.updateAllViews(airportCode);
    }
}

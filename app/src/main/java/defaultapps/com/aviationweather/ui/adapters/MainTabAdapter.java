package defaultapps.com.aviationweather.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.ui.fragments.MetarFragment;
import defaultapps.com.aviationweather.ui.fragments.TafFragment;
import defaultapps.com.aviationweather.miscs.MyApplication;

/**
 * Created on 2/1/2017.
 */

public class MainTabAdapter extends FragmentPagerAdapter {

    private String[] tabNames;

    private MetarFragment metarFragment;
    private TafFragment tafFragment;



    public MainTabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        tabNames = MyApplication.getAppContext().getResources().getStringArray(R.array.tab_names);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MetarFragment();
            case 1:
                return new TafFragment();
            default:
                return null;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        switch (position) {
            case 0:
                metarFragment = (MetarFragment) createdFragment;
                break;
            case 1:
                tafFragment = (TafFragment) createdFragment;
                break;    
        }
        return createdFragment;
    }


    public Fragment getFragment(int position) {
        switch (position) {
            case 0:
                return metarFragment;
            case 1:
                return tafFragment;
            default:
                return metarFragment;
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
}

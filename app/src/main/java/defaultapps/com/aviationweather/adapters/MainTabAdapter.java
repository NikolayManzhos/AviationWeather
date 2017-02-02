package defaultapps.com.aviationweather.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.HashMap;

import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.fragments.MetarFragment;
import defaultapps.com.aviationweather.fragments.TafFragment;
import defaultapps.com.aviationweather.miscs.MyApplication;

/**
 * Created on 2/1/2017.
 */

public class MainTabAdapter extends FragmentStatePagerAdapter {

    private String[] tabNames;

    private HashMap<Integer, Fragment> mPageReferenceMap;




    public MainTabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        tabNames = MyApplication.getAppContext().getResources().getStringArray(R.array.tab_names);
        mPageReferenceMap = new HashMap<>();
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MetarFragment metarFragment = new MetarFragment();
                mPageReferenceMap.put(position, metarFragment);
                return metarFragment;
            case 1:
                TafFragment tafFragment = new TafFragment();
                mPageReferenceMap.put(position, tafFragment);
                return tafFragment;
            default:
                MetarFragment metarFragment2 = new MetarFragment();
                mPageReferenceMap.put(position, metarFragment2);
                return metarFragment2;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        mPageReferenceMap.remove(position);
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }

    public MetarFragment getMetarFragment(int position) {
        return (MetarFragment) mPageReferenceMap.get(position);
    }

    public TafFragment geTafFragment(int position) {
        return (TafFragment) mPageReferenceMap.get(position);
    }
}

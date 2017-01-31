package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;
import defaultapps.com.aviationweather.R;
import defaultapps.com.aviationweather.adapters.MainTabAdapter;

/**
 * Created on 1/31/2017.
 */

public class ForecastFragment extends Fragment {

    private Unbinder unbinder;


    @Nullable
    @BindView(R.id.forecast_tab_layout)
    TabLayout forecastTab;

    @Nullable
    @BindView(R.id.forecast_view_pager)
    ViewPager forecastPager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }
        View swipeRefreshLayout = inflater.inflate(R.layout.fragment_forecast, container, false);
        unbinder = ButterKnife.bind(this, swipeRefreshLayout);

        if (forecastPager != null) {
            forecastPager.setAdapter(new MainTabAdapter(getChildFragmentManager()));
            forecastPager.setOffscreenPageLimit(2);
            forecastTab.setupWithViewPager(forecastPager);
        }


        return swipeRefreshLayout;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

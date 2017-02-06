package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import defaultapps.com.aviationweather.controllers.MetarController;
import defaultapps.com.aviationweather.controllers.TafController;
import defaultapps.com.aviationweather.interfaces.OnSuccesMetarCallback;
import defaultapps.com.aviationweather.interfaces.OnSuccessTafCallback;
import defaultapps.com.aviationweather.interfaces.OnErrorCallback;
import defaultapps.com.aviationweather.views.MainView;

/**
 * Created on 2/2/2017.
 */

public class ProcessingFragment extends Fragment implements OnErrorCallback, OnSuccesMetarCallback, OnSuccessTafCallback {

    private MainView mainView;
    private String metar;
    private String taf;
    private MetarController metarController;
    private TafController tafController;

    private MetarFragment metarFragment;
    private TafFragment tafFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (metar != null) {
            metarFragment.updateViews(metar);
        } else if (taf != null) {
            tafFragment.updateViews(taf);
        }
    }

    public void setMainView(MainView mainView) { this.mainView = mainView; }

    public void submitQuery(String query) {
        metarFragment.showProgressBar();
        tafFragment.showProgressBar();
        metarController.updateMetar(query);
        tafController.updateTaf(query);
    }

    public void setFragments(MetarFragment metarFragment, TafFragment tafFragment) {
        this.metarFragment = metarFragment;
        this.tafFragment = tafFragment;
        metarController = new MetarController(this, this);
        tafController = new TafController(this, this);
    }

    @Override
    public void rawMetarSuccess(String rawMetar) {
        metar = rawMetar;
        metarFragment.updateViews(rawMetar);
        metarFragment.hideProgressBar();
    }

    @Override
    public void rawTafSuccess(String rawTaf) {
        taf = rawTaf;
        tafFragment.updateViews(rawTaf);
        tafFragment.hideProgressBar();
    }

    @Override
    public void wrongAirportCode() {
        mainView.showErrorSnackbar();
        metarFragment.hideProgressBar();
        tafFragment.hideProgressBar();
    }

    @Override
    public void badConnection() {
        tafFragment.hideProgressBar();
        metarFragment.hideProgressBar();
    }
}

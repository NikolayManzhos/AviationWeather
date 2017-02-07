package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

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
    private MetarController metarController;
    private TafController tafController;

    private MetarFragment metarFragment;
    private TafFragment tafFragment;

    private final String TAG = "ProcessingFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        metarController = new MetarController(this, this);
        tafController = new TafController(this, this);
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
    }

    @Override
    public void rawMetarSuccess(String rawMetar) {
        metarFragment.updateViews(rawMetar);
        mainView.showFavoriteButton();
    }

    @Override
    public void rawTafSuccess(String rawTaf) {
        tafFragment.updateViews(rawTaf);
        mainView.showFavoriteButton();
    }

    @Override
    public void wrongAirportCode() {
        mainView.showErrorSnackbar();
        metarFragment.hideProgressBar();
        tafFragment.hideProgressBar();
        mainView.hideFavoriteButton();
    }

    @Override
    public void badConnection() {
        tafFragment.hideProgressBar();
        metarFragment.hideProgressBar();
        mainView.hideFavoriteButton();
    }
}

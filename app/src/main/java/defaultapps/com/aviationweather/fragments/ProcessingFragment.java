package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;

import defaultapps.com.aviationweather.controllers.MetarController;
import defaultapps.com.aviationweather.controllers.TafController;
import defaultapps.com.aviationweather.interfaces.OnSuccesMetarCallback;
import defaultapps.com.aviationweather.interfaces.OnSuccessTafCallback;
import defaultapps.com.aviationweather.interfaces.OnErrorCallback;
import defaultapps.com.aviationweather.miscs.PreferencesManager;
import defaultapps.com.aviationweather.views.MainView;

/**
 * Created on 2/2/2017.
 */

public class ProcessingFragment extends Fragment implements OnErrorCallback, OnSuccesMetarCallback, OnSuccessTafCallback {

    private MainView mainView;
    private MetarController metarController = new MetarController(this, this);
    private TafController tafController = new TafController(this, this);

    private MetarFragment metarFragment;
    private TafFragment tafFragment;

    private String currentAirCode;

    private final String TAG = "ProcessingFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            currentAirCode = savedInstanceState.getString("currentCode");
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
    }

    @Override
    public void metarSuccess(String airCode, ArrayList<String> data) {
        metarFragment.updateViews(data.get(0));
        currentAirCode = airCode;
        mainView.showFavoriteButton();
    }

    @Override
    public void tafSuccess(String airCode, ArrayList<String> data) {
        tafFragment.updateViews(data.get(0));
        currentAirCode = airCode;
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

    public String getCurrentAirCode() {
        return currentAirCode;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentCode", currentAirCode);
    }
}

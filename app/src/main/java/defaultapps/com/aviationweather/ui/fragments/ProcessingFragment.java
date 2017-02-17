package defaultapps.com.aviationweather.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import defaultapps.com.aviationweather.controllers.MetarController;
import defaultapps.com.aviationweather.controllers.TafController;
import defaultapps.com.aviationweather.interfaces.OnSuccesMetarCallback;
import defaultapps.com.aviationweather.interfaces.OnSuccessTafCallback;
import defaultapps.com.aviationweather.interfaces.OnErrorCallback;
import defaultapps.com.aviationweather.miscs.PreferencesManager;
import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.models.taf.TAF;
import defaultapps.com.aviationweather.ui.views.MainView;

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

    private boolean isSavedStateNull = false;

    private List<String> dataMetar;
    private List<String> dataTaf;

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
        } else {
            isSavedStateNull = true;
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
        if (isSavedStateNull) {
            METAR metarSavedModel = PreferencesManager.get().getSavedMetar();
            TAF tafSavedModel = PreferencesManager.get().getSavedTaf();
            if (metarSavedModel != null) {
                metarFragment.updateViews(metarController.parseMetarModel(metarSavedModel));
            }
            if (tafSavedModel != null) {
                tafFragment.updateViews(tafController.parseTafModel(tafSavedModel));
            }
            isSavedStateNull = false;
        }
        if (dataMetar != null) {
            metarFragment.updateViews(dataMetar);
            dataMetar = null;
        }
        if (dataTaf != null) {
            tafFragment.updateViews(dataTaf);
            dataTaf = null;
        }
    }

    @Override
    public void metarSuccess(String airCode, List<String> data) {
        metarFragment.updateViews(data);
        currentAirCode = airCode;
        mainView.showFavoriteButton();
        mainView.showRefreshButton();
    }

    @Override
    public void tafSuccess(String airCode, List<String> data) {
        tafFragment.updateViews(data);
//        currentAirCode = airCode;
////        mainView.showFavoriteButton();
////        mainView.showRefreshButton();
    }

    @Override
    public void wrongAirportCode() {
        mainView.showErrorSnackbar();
        metarFragment.hideProgressBar();
        tafFragment.hideProgressBar();
        if (!PreferencesManager.get().getCurrentAirCode().equals("none")) {
            mainView.showRefreshButton();
            mainView.showRefreshButton();
        } else {
            mainView.hideRefreshButton();
        }
    }

    @Override
    public void badConnection() {
        tafFragment.hideProgressBar();
        metarFragment.hideProgressBar();
        if (!PreferencesManager.get().getCurrentAirCode().equals("none")) {
            mainView.showRefreshButton();
            mainView.showFavoriteButton();
        } else {
            mainView.hideRefreshButton();
        }
    }

    @Deprecated
    public String getCurrentAirCode() {
        return currentAirCode;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentCode", currentAirCode);
    }
}

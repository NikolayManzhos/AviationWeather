package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;

import defaultapps.com.aviationweather.controllers.MainController;
import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.models.taf.TAF;
import defaultapps.com.aviationweather.views.MainView;

/**
 * Created on 2/2/2017.
 */

public class ProcessingFragment extends Fragment implements MainController.OnFinishDownloading, SearchView.OnQueryTextListener {

    private MainView mainView;
    private METAR metar;
    private TAF taf;
    private MainController mainController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mainController = new MainController(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (metar != null) {
            mainView.updateMetarUi(metar.getRawReport());
        } else if (taf != null) {
            mainView.updateTafUi(taf.getRawReport());
        }
    }

    public void setMainView(MainView mainView) { this.mainView = mainView; }


    @Override
    public void downloadFinishedMetar(METAR metar) {
        this.metar = metar;
        if (mainView != null) {
            mainView.updateMetarUi(metar.getRawReport());
        }
    }

    @Override
    public void downloadFinishedTaf(TAF taf) {
        this.taf = taf;
        if (mainView != null) {
            mainView.updateTafUi(taf.getRawReport());
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mainController.getTaf(query);
        mainController.getMetar(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

package defaultapps.com.aviationweather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;

import defaultapps.com.aviationweather.controllers.MetarController;
import defaultapps.com.aviationweather.controllers.TafController;
import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.models.taf.TAF;
import defaultapps.com.aviationweather.views.MainView;
import defaultapps.com.aviationweather.views.MetarView;
import defaultapps.com.aviationweather.views.TafView;

/**
 * Created on 2/2/2017.
 */

public class ProcessingFragment extends Fragment implements MetarView, TafView {

    private MainView mainView;
    private String metar;
    private String taf;
    private MetarController metarController;
    private TafController tafController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        metarController = new MetarController(this);
        tafController = new TafController(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (metar != null) {
            mainView.updateMetarUi(metar);
        } else if (taf != null) {
            mainView.updateTafUi(taf);
        }
    }

    public void setMainView(MainView mainView) { this.mainView = mainView; }




    @Override
    public void updateRawMetar(String rawMetar) {
        metar = rawMetar;
        if (mainView != null) {
            mainView.updateMetarUi(rawMetar);
        }
    }

    @Override
    public void updateRawTaf(String rawTaf) {
        taf = rawTaf;
        if (mainView != null) {
            mainView.updateTafUi(rawTaf);
        }
    }

    public void submitQuery(String query) {
        metarController.updateMetar(query);
        tafController.updateTaf(query);
    }

}

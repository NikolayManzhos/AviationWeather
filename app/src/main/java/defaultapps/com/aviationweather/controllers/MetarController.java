package defaultapps.com.aviationweather.controllers;

import android.util.Log;

import defaultapps.com.aviationweather.interfaces.OnWrongCodeCallback;
import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.miscs.MyApplication;
import defaultapps.com.aviationweather.views.MetarView;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created on 2/1/2017.
 */

public class MetarController {

    private MetarView metarView;
    private METAR metar;
    private String rawMetar;

    private OnWrongCodeCallback onWrongCodeCallback;

    public MetarController(MetarView metarView, OnWrongCodeCallback onWrongCodeCallback) {
        this.metarView = metarView;
        this.onWrongCodeCallback = onWrongCodeCallback;
    }

    public void updateMetar(String airportCode) {
        MyApplication.getWeatherAPI().getMetarData(airportCode).enqueue(new Callback<METAR>() {
            @Override
            public void onResponse(Call<METAR> call, retrofit2.Response<METAR> response) {
                metar = response.body();
                if (metar.getError() == null) {
                    rawMetar = metar.getRawReport();
                    metarView.updateRawMetar(rawMetar);
                } else {
                    Log.i(MetarController.class.getName(),metar.getError());
                    onWrongCodeCallback.wrongAirportCode();
                }
            }

            @Override
            public void onFailure(Call<METAR> call, Throwable t) {
                Log.i(MetarController.class.getName(), "FAIL");
            }
        });

    }



}

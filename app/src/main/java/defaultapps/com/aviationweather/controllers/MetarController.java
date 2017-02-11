package defaultapps.com.aviationweather.controllers;

import android.util.Log;

import java.util.ArrayList;

import defaultapps.com.aviationweather.interfaces.OnSuccesMetarCallback;
import defaultapps.com.aviationweather.interfaces.OnErrorCallback;
import defaultapps.com.aviationweather.miscs.PreferencesManager;
import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.miscs.MyApplication;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created on 2/1/2017.
 */

public class MetarController {

    public METAR metar = new METAR();
    private String rawMetar;

    private OnErrorCallback onErrorCallback;
    private OnSuccesMetarCallback onSuccesMetarCallback;


    public MetarController(OnErrorCallback onErrorCallback, OnSuccesMetarCallback onSuccesMetarCallback) {
        this.onErrorCallback = onErrorCallback;
        this.onSuccesMetarCallback = onSuccesMetarCallback;
    }

    public void updateMetar(final String airportCode) {
        MyApplication.getWeatherAPI().getMetarData(airportCode).enqueue(new Callback<METAR>() {
            @Override
            public void onResponse(Call<METAR> call, retrofit2.Response<METAR> response) {
                metar = response.body();
                if (metar.getError() == null) {
                    ArrayList<String> data = new ArrayList<String>();
                    data.add(metar.getRawReport());
                    onSuccesMetarCallback.metarSuccess(metar.getStation(), data);
                    PreferencesManager.get().setCurrentAirCode(metar.getStation());
                } else {
                    Log.i(MetarController.class.getName(),metar.getError());
                    onErrorCallback.wrongAirportCode();
                }
            }

            @Override
            public void onFailure(Call<METAR> call, Throwable t) {
                Log.i(MetarController.class.getName(), t.toString());
                onErrorCallback.badConnection();
            }
        });

    }



}

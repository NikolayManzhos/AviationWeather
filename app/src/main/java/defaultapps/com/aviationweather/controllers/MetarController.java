package defaultapps.com.aviationweather.controllers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
                    onSuccesMetarCallback.metarSuccess(metar.getStation(), parseMetarModel(metar));
                    PreferencesManager.get().setCurrentAirCode(metar.getStation());
                    PreferencesManager.get().setSavedMetar(metar);
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

    public List<String> parseMetarModel(METAR metar) {
        List<String> data = new ArrayList<>();
        data.add(metar.getRawReport());
        data.add(metar.getTranslations().getAltimeter());
        data.add(metar.getTranslations().getClouds());
        data.add(metar.getTranslations().getDewpoint());
        data.add(metar.getTranslations().getTemperature());
        data.add(metar.getTranslations().getVisibility());
        data.add(metar.getTranslations().getWind());
        data.add(metar.getTranslations().getOther());
        return data;
    }



}

package defaultapps.com.aviationweather.controllers;

import android.util.Log;

import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.utils.MyApplication;
import defaultapps.com.aviationweather.views.MetarView;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created on 2/1/2017.
 */

public class MetarController {

    private MetarView metarView;
    public METAR metar;
    private String rawMetar;

    public MetarController(MetarView metarView) {
        this.metarView = metarView;
    }

    public void updateMetar(String airportCode) {
        MyApplication.getWeatherAPI().getMetarData(airportCode).enqueue(new Callback<METAR>() {
            @Override
            public void onResponse(Call<METAR> call, retrofit2.Response<METAR> response) {
                metar = response.body();
                rawMetar = metar.getRawReport();
                Log.i(MetarController.class.getName(),rawMetar);
                metarView.updateRawMetar(rawMetar);
            }

            @Override
            public void onFailure(Call<METAR> call, Throwable t) {
                Log.i(MetarController.class.getName(), "FAIL");
            }
        });

    }



}

package defaultapps.com.aviationweather.controllers;

import android.util.Log;

import java.util.ArrayList;

import defaultapps.com.aviationweather.interfaces.OnErrorCallback;
import defaultapps.com.aviationweather.interfaces.OnSuccessTafCallback;
import defaultapps.com.aviationweather.models.taf.TAF;
import defaultapps.com.aviationweather.miscs.MyApplication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 2/1/2017.
 */

public class TafController {

    public TAF tafModel = new TAF();

    private OnSuccessTafCallback onSuccessTafCallback;
    private OnErrorCallback onErrorCallback;


    private String tafRaw;

    public TafController( OnSuccessTafCallback onSuccessTafCallback, OnErrorCallback onErrorCallback) {
        this.onSuccessTafCallback = onSuccessTafCallback;
        this.onErrorCallback = onErrorCallback;
    }

    public void updateTaf(String airportCode) {
        MyApplication.getWeatherAPI().getTafData(airportCode).enqueue(new Callback<TAF>() {
            @Override
            public void onResponse(Call<TAF> call, Response<TAF> response) {
                tafModel = response.body();
                if (tafModel.getError() == null) {
                    ArrayList<String> data = new ArrayList<String>();
                    data.add(tafModel.getRawReport());
                    onSuccessTafCallback.tafSuccess(tafModel.getStation(), data);
                } else {
                    Log.i(TafController.class.getName(), tafModel.getError());
                    onErrorCallback.wrongAirportCode();
                }
            }

            @Override
            public void onFailure(Call<TAF> call, Throwable t) {
                onErrorCallback.badConnection();
            }
        });
    }
}

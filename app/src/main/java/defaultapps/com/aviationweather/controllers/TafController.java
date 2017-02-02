package defaultapps.com.aviationweather.controllers;

import defaultapps.com.aviationweather.models.taf.TAF;
import defaultapps.com.aviationweather.miscs.MyApplication;
import defaultapps.com.aviationweather.views.TafView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 2/1/2017.
 */

public class TafController {

    private TafView tafView;
    private TAF tafModel;

    private String tafRaw;

    public TafController(TafView tafView) {
        this.tafView = tafView;
    }

    public void updateTaf(String airportCode) {
        MyApplication.getWeatherAPI().getTafData(airportCode).enqueue(new Callback<TAF>() {
            @Override
            public void onResponse(Call<TAF> call, Response<TAF> response) {
                tafModel = response.body();
                tafRaw = tafModel.getRawReport();
                tafView.updateRawTaf(tafRaw);

            }

            @Override
            public void onFailure(Call<TAF> call, Throwable t) {

            }
        });
    }
}

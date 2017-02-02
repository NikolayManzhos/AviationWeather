package defaultapps.com.aviationweather.controllers;

import defaultapps.com.aviationweather.miscs.MyApplication;
import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.models.taf.TAF;
import defaultapps.com.aviationweather.views.MetarView;
import defaultapps.com.aviationweather.views.TafView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 2/2/2017.
 */

public class MainController  {

    private OnFinishDownloading finishDownloading;


    public MainController(OnFinishDownloading finishDownloading) {
        this.finishDownloading = finishDownloading;
    }

    public void getMetar(String airportCode) {
        MyApplication.getWeatherAPI().getMetarData(airportCode).enqueue(new Callback<METAR>() {
            @Override
            public void onResponse(Call<METAR> call, Response<METAR> response) {
                finishDownloading.downloadFinishedMetar(response.body());
            }

            @Override
            public void onFailure(Call<METAR> call, Throwable t) {

            }
        });
    }

    public void getTaf(String airportCode) {
        MyApplication.getWeatherAPI().getTafData(airportCode).enqueue(new Callback<TAF>() {
            @Override
            public void onResponse(Call<TAF> call, Response<TAF> response) {
                finishDownloading.downloadFinishedTaf(response.body());
            }

            @Override
            public void onFailure(Call<TAF> call, Throwable t) {

            }
        });
    }

    public interface OnFinishDownloading {
        void downloadFinishedMetar(METAR metar);

        void downloadFinishedTaf(TAF taf);
    }
}

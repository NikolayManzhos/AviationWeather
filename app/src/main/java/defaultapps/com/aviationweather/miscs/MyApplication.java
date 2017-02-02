package defaultapps.com.aviationweather.miscs;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import defaultapps.com.aviationweather.api.AviationWeatherAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 1/30/2017.
 */

public class MyApplication extends Application {

    private static Context mContext;

    private static AviationWeatherAPI aviationWeatherAPI;
    private Retrofit retrofit;

    private String baseUrl = "https://avwx.rest/";

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new MaterialModule());
        mContext = getApplicationContext();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aviationWeatherAPI = retrofit.create(AviationWeatherAPI.class);

    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AviationWeatherAPI getWeatherAPI() {
        return aviationWeatherAPI;
    }
}

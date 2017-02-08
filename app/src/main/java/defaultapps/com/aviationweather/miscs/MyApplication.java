package defaultapps.com.aviationweather.miscs;

import android.app.Application;
import android.content.Context;

import com.google.gson.annotations.Expose;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import defaultapps.com.aviationweather.api.AviationWeatherAPI;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 1/30/2017.
 */

public class MyApplication extends Application {

    private static Context mContext;

    private static AviationWeatherAPI aviationWeatherAPI;
    private Retrofit retrofit;

    private final String baseUrl = "https://avwx.rest/";

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new MaterialModule());
        mContext = getApplicationContext();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
        aviationWeatherAPI = retrofit.create(AviationWeatherAPI.class);

    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AviationWeatherAPI getWeatherAPI() {
        return aviationWeatherAPI;
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }



}

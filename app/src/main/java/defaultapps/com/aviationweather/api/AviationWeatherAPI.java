package defaultapps.com.aviationweather.api;

import defaultapps.com.aviationweather.models.metar.METAR;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created on 1/30/2017.
 */

public interface AviationWeatherAPI {

    @GET("api/{forecastKind}/{airportCode}")
    Call<METAR> getData(@Path("forecastKind") String forecastKind, @Path("airportCode") String airportCode);
}

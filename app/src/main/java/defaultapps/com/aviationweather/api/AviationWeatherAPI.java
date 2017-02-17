package defaultapps.com.aviationweather.api;

import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.models.taf.TAF;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created on 1/30/2017.
 */

public interface AviationWeatherAPI {

    @GET("api/metar/{airportCode}?options=info,translate")
    Call<METAR> getMetarData(@Path("airportCode") String airportCode);

    @GET("api/taf/{airportCode}?options=summary")
    Call<TAF> getTafData(@Path("airportCode") String airportCode);
}

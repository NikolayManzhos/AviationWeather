
package defaultapps.com.aviationweather.models.taf;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translations {

    @SerializedName("Forecast")
    @Expose
    private List<ForecastTranslated> forecast = null;
    @SerializedName("Max-Temp")
    @Expose
    private String maxTemp;
    @SerializedName("Min-Temp")
    @Expose
    private String minTemp;

    public List<ForecastTranslated> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastTranslated> forecast) {
        this.forecast = forecast;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

}

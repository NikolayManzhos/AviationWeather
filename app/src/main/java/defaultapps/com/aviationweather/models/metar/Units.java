
package defaultapps.com.aviationweather.models.metar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Units {

    @SerializedName("Altimeter")
    @Expose
    private String altimeter;
    @SerializedName("Altitude")
    @Expose
    private String altitude;
    @SerializedName("Temperature")
    @Expose
    private String temperature;
    @SerializedName("Visibility")
    @Expose
    private String visibility;
    @SerializedName("Wind-Speed")
    @Expose
    private String windSpeed;

    public String getAltimeter() {
        return altimeter;
    }

    public void setAltimeter(String altimeter) {
        this.altimeter = altimeter;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

}

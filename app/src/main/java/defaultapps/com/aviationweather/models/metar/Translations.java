
package defaultapps.com.aviationweather.models.metar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Translations {

    @SerializedName("Altimeter")
    @Expose
    private String altimeter;
    @SerializedName("Clouds")
    @Expose
    private String clouds;
    @SerializedName("Dewpoint")
    @Expose
    private String dewpoint;
    @SerializedName("Other")
    @Expose
    private String other;
    @SerializedName("Temperature")
    @Expose
    private String temperature;
    @SerializedName("Visibility")
    @Expose
    private String visibility;
    @SerializedName("Wind")
    @Expose
    private String wind;

    public String getAltimeter() {
        return altimeter;
    }

    public void setAltimeter(String altimeter) {
        this.altimeter = altimeter;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

}

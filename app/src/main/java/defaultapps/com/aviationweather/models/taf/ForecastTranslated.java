
package defaultapps.com.aviationweather.models.taf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForecastTranslated {

    @SerializedName("Altimeter")
    @Expose
    private String altimeter;
    @SerializedName("Clouds")
    @Expose
    private String clouds;
    @SerializedName("Icing")
    @Expose
    private String icing;
    @SerializedName("Other")
    @Expose
    private String other;
    @SerializedName("Turbulance")
    @Expose
    private String turbulance;
    @SerializedName("Visibility")
    @Expose
    private String visibility;
    @SerializedName("Wind")
    @Expose
    private String wind;
    @SerializedName("Wind-Shear")
    @Expose
    private String windShear;

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

    public String getIcing() {
        return icing;
    }

    public void setIcing(String icing) {
        this.icing = icing;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getTurbulance() {
        return turbulance;
    }

    public void setTurbulance(String turbulance) {
        this.turbulance = turbulance;
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

    public String getWindShear() {
        return windShear;
    }

    public void setWindShear(String windShear) {
        this.windShear = windShear;
    }

}

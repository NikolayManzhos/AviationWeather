
package defaultapps.com.aviationweather.models.metar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Elevation")
    @Expose
    private String elevation;
    @SerializedName("IATA")
    @Expose
    private String iATA;
    @SerializedName("ICAO")
    @Expose
    private String iCAO;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Priority")
    @Expose
    private String priority;
    @SerializedName("State")
    @Expose
    private String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getIATA() {
        return iATA;
    }

    public void setIATA(String iATA) {
        this.iATA = iATA;
    }

    public String getICAO() {
        return iCAO;
    }

    public void setICAO(String iCAO) {
        this.iCAO = iCAO;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}

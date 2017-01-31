
package defaultapps.com.aviationweather.models.taf;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("Altimeter")
    @Expose
    private String altimeter;
    @SerializedName("Cloud-List")
    @Expose
    private List<List<String>> cloudList = null;
    @SerializedName("End-Time")
    @Expose
    private String endTime;
    @SerializedName("Flight-Rules")
    @Expose
    private String flightRules;
    @SerializedName("Icing-List")
    @Expose
    private List<Object> icingList = null;
    @SerializedName("Other-List")
    @Expose
    private List<String> otherList = null;
    @SerializedName("Probability")
    @Expose
    private String probability;
    @SerializedName("Raw-Line")
    @Expose
    private String rawLine;
    @SerializedName("Start-Time")
    @Expose
    private String startTime;
    @SerializedName("Turb-List")
    @Expose
    private List<Object> turbList = null;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Visibility")
    @Expose
    private String visibility;
    @SerializedName("Wind-Direction")
    @Expose
    private String windDirection;
    @SerializedName("Wind-Gust")
    @Expose
    private String windGust;
    @SerializedName("Wind-Shear")
    @Expose
    private String windShear;
    @SerializedName("Wind-Speed")
    @Expose
    private String windSpeed;

    public String getAltimeter() {
        return altimeter;
    }

    public void setAltimeter(String altimeter) {
        this.altimeter = altimeter;
    }

    public List<List<String>> getCloudList() {
        return cloudList;
    }

    public void setCloudList(List<List<String>> cloudList) {
        this.cloudList = cloudList;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFlightRules() {
        return flightRules;
    }

    public void setFlightRules(String flightRules) {
        this.flightRules = flightRules;
    }

    public List<Object> getIcingList() {
        return icingList;
    }

    public void setIcingList(List<Object> icingList) {
        this.icingList = icingList;
    }

    public List<String> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<String> otherList) {
        this.otherList = otherList;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getRawLine() {
        return rawLine;
    }

    public void setRawLine(String rawLine) {
        this.rawLine = rawLine;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<Object> getTurbList() {
        return turbList;
    }

    public void setTurbList(List<Object> turbList) {
        this.turbList = turbList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindGust() {
        return windGust;
    }

    public void setWindGust(String windGust) {
        this.windGust = windGust;
    }

    public String getWindShear() {
        return windShear;
    }

    public void setWindShear(String windShear) {
        this.windShear = windShear;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

}

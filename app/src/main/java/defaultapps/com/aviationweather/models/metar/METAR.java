
package defaultapps.com.aviationweather.models.metar;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class METAR {

    @SerializedName("Altimeter")
    @Expose
    private String altimeter;
    @SerializedName("Cloud-List")
    @Expose
    private List<List<String>> cloudList = null;
    @SerializedName("Dewpoint")
    @Expose
    private String dewpoint;
    @SerializedName("Flight-Rules")
    @Expose
    private String flightRules;
    @SerializedName("Info")
    @Expose
    private Info info;
    @SerializedName("Other-List")
    @Expose
    private List<String> otherList = null;
    @SerializedName("Raw-Report")
    @Expose
    private String rawReport;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Remarks-Info")
    @Expose
    private RemarksInfo remarksInfo;
    @SerializedName("Runway-Vis-List")
    @Expose
    private List<Object> runwayVisList = null;
    @SerializedName("Station")
    @Expose
    private String station;
    @SerializedName("Temperature")
    @Expose
    private String temperature;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("Translations")
    @Expose
    private Translations translations;
    @SerializedName("Units")
    @Expose
    private Units units;
    @SerializedName("Visibility")
    @Expose
    private String visibility;
    @SerializedName("Wind-Direction")
    @Expose
    private String windDirection;
    @SerializedName("Wind-Gust")
    @Expose
    private String windGust;
    @SerializedName("Wind-Speed")
    @Expose
    private String windSpeed;
    @SerializedName("Wind-Variable-Dir")
    @Expose
    private List<Object> windVariableDir = null;

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

    public String getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(String dewpoint) {
        this.dewpoint = dewpoint;
    }

    public String getFlightRules() {
        return flightRules;
    }

    public void setFlightRules(String flightRules) {
        this.flightRules = flightRules;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<String> getOtherList() {
        return otherList;
    }

    public void setOtherList(List<String> otherList) {
        this.otherList = otherList;
    }

    public String getRawReport() {
        return rawReport;
    }

    public void setRawReport(String rawReport) {
        this.rawReport = rawReport;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public RemarksInfo getRemarksInfo() {
        return remarksInfo;
    }

    public void setRemarksInfo(RemarksInfo remarksInfo) {
        this.remarksInfo = remarksInfo;
    }

    public List<Object> getRunwayVisList() {
        return runwayVisList;
    }

    public void setRunwayVisList(List<Object> runwayVisList) {
        this.runwayVisList = runwayVisList;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
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

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public List<Object> getWindVariableDir() {
        return windVariableDir;
    }

    public void setWindVariableDir(List<Object> windVariableDir) {
        this.windVariableDir = windVariableDir;
    }

}

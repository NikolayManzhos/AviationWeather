
package defaultapps.com.aviationweather.models.taf;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TAF {

    @SerializedName("Forecast")
    @Expose
    private List<Forecast> forecast = null;
    @SerializedName("Info")
    @Expose
    private Info info;
    @SerializedName("Max-Temp")
    @Expose
    private String maxTemp;
    @SerializedName("Min-Temp")
    @Expose
    private String minTemp;
    @SerializedName("Raw-Report")
    @Expose
    private String rawReport;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("Station")
    @Expose
    private String station;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("Translations")
    @Expose
    private Translations translations;
    @SerializedName("Units")
    @Expose
    private Units units;

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
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

}

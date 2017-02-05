package defaultapps.com.aviationweather.views;

/**
 * Created on 2/2/2017.
 */

public interface MainView {
    void updateMetarUi(String metarRaw);
    void updateTafUi(String tafRaw);
    void showErrorSnackbar();
}

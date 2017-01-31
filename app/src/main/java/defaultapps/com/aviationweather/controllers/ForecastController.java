package defaultapps.com.aviationweather.controllers;

import java.util.ArrayList;

/**
 * Created on 2/1/2017.
 */

public class ForecastController {

    public ForecastController() {

    }

    public interface ForecastView {
        void setRawMetar(String rawMetar);

        void updateTranslatedMetar(ArrayList<String> translatedMetar);
    }
}

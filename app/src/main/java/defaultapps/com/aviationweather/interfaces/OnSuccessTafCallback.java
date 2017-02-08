package defaultapps.com.aviationweather.interfaces;

import java.util.ArrayList;

/**
 * Created on 2/6/2017.
 */

public interface OnSuccessTafCallback {
    void tafSuccess(String airCode, ArrayList<String> data);
}

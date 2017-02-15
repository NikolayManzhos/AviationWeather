package defaultapps.com.aviationweather.interfaces;

import java.util.List;

/**
 * Created on 2/6/2017.
 */

public interface OnSuccessTafCallback {
    void tafSuccess(String airCode, List<String> data);
}

package defaultapps.com.aviationweather.interfaces;

import java.util.ArrayList;

/**
 * Created on 2/6/2017.
 */

public interface OnSuccesMetarCallback {
    void metarSuccess(String airCode, ArrayList<String> data);
}

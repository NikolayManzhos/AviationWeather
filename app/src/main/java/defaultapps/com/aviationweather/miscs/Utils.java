package defaultapps.com.aviationweather.miscs;

import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;

import defaultapps.com.aviationweather.R;

/**
 * Created on 2/2/2017.
 */

public class Utils {

    private Utils() {}

    public static void setMenuIcon(MenuItem menuItem, MaterialIcons icon) {
        menuItem.setIcon(
                new IconDrawable(MyApplication.getAppContext(), icon)
                        .colorRes(R.color.textColorPrimary)
                        .actionBarSize());
    }
}

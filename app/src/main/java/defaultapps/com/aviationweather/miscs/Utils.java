package defaultapps.com.aviationweather.miscs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.View;

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
    
    public static void showSnackbar(View parentView, String message) {
        final Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackView.setBackgroundColor(MyApplication.getAppContext().getResources().getColor(R.color.colorPrimary));
        snackbar.setAction(MyApplication.getAppContext().getResources().getString(R.string.dismiss_snackbar), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    public static boolean checkIfHasNetwork()
    {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}

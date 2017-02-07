package defaultapps.com.aviationweather.miscs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created on 2/6/2017.
 */

public class PreferencesManager {

    private final String FIRST_TIME_USER = "firstTime";
    private final String SAVED_METAR = "savedMetar";
    private final String SAVED_TAF = "savedTaf";
    private final String FAVORITE_AIRPORTS = "favoriteAirports";


    private SharedPreferences preferences;
    private static PreferencesManager instance;


    public static PreferencesManager get() {
        if (instance == null) {
            instance = getSync();
        }
        return instance;
    }

    private static synchronized PreferencesManager getSync() {
        if (instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }

    private PreferencesManager() {
        preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext());
    }

    public boolean getFirstTimeUser(){
        return preferences.getBoolean(FIRST_TIME_USER, true);
    }

    public void setFirstTimeUser(boolean firstTimeUser) {
        preferences.edit().putBoolean(FIRST_TIME_USER, firstTimeUser).apply();
    }

    public String getSavedMetar() {
        return preferences.getString(SAVED_METAR, "none");
    }

    public void setSavedMetar(String metarJson) {
        preferences.edit().putString(SAVED_METAR, metarJson).apply();
    }

    public String getSavedTaf() {
        return preferences.getString(SAVED_TAF, "none");
    }

    public void setSavedTaf(String tafJson) {
        preferences.edit().putString(SAVED_TAF, tafJson).apply();
    }

    public Set<String> getFavoriteAirports() {
        return preferences.getStringSet(FAVORITE_AIRPORTS, null);
    }

    public void setFavoriteAirports(Set<String> favoriteAirports) {
        preferences.edit().putStringSet(FAVORITE_AIRPORTS, favoriteAirports).apply();
    }
}

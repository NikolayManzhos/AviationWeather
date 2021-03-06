package defaultapps.com.aviationweather.miscs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import defaultapps.com.aviationweather.models.metar.METAR;
import defaultapps.com.aviationweather.models.taf.TAF;

/**
 * Created on 2/6/2017.
 */

public class PreferencesManager {

    private final String FIRST_TIME_USER = "firstTime";
    private final String SAVED_METAR = "savedMetar";
    private final String SAVED_TAF = "savedTaf";
    private final String FAVORITE_AIRPORTS = "favoriteAirports";
    private final String CURRENT_AIR_CODE = "airCodeC";


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

    public METAR getSavedMetar() {
        Gson gson = new Gson();
        if (!preferences.getString(SAVED_METAR, "none").equals("none")) {
            return gson.fromJson(preferences.getString(SAVED_METAR, "none"), METAR.class);
        } else {
            return null;
        }
    }

    public void setSavedMetar(METAR metar) {
        Gson gson = new Gson();
        preferences.edit().putString(SAVED_METAR, gson.toJson(metar)).apply();
    }

    public TAF getSavedTaf() {
        Gson gson = new Gson();
        if (!preferences.getString(SAVED_TAF, "none").equals("none")) {
            return gson.fromJson(preferences.getString(SAVED_TAF, "none"), TAF.class);
        } else {
            return null;
        }
    }

    public void setSavedTaf(TAF taf) {
        Gson gson = new Gson();
        preferences.edit().putString(SAVED_TAF, gson.toJson(taf)).apply();
    }

    public List<String> getFavoriteAirports() {
        String favAirports = preferences.getString(FAVORITE_AIRPORTS, "none");
        return !favAirports.equals("none") ? new ArrayList<String>(Arrays.asList(favAirports.split(", "))) : new ArrayList<String>();
    }

    public void setFavoriteAirport(String favoriteAirport) {
        List<String> airports = getFavoriteAirports();
        airports.add(favoriteAirport);
        String airportsString = airports.toString().replaceAll("^\\[|]$", "");
        preferences.edit().putString(FAVORITE_AIRPORTS, airportsString).apply();
    }

    public void deleteFavoriteAirport(String aiportCode) {
        Set<String> airports = new LinkedHashSet<>();
        airports.addAll(getFavoriteAirports());
        airports.remove(aiportCode);
        preferences.edit().putStringSet(FAVORITE_AIRPORTS, airports).apply();
    }

    public String getCurrentAirCode() {
        return preferences.getString(CURRENT_AIR_CODE, "none");
    }

    public void setCurrentAirCode(String airCode) {
        preferences.edit().putString(CURRENT_AIR_CODE, airCode).apply();
    }
}

package gusev.max.readytostudy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

public class SharedPrefManager {

    private final String TOKEN = "TOKEN";

    private static SharedPrefManager sInstance;
    private SharedPreferences prefs;
    private String token;

    private SharedPrefManager(@NonNull Context context) {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void init(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new SharedPrefManager(context);
        }
    }

    public static SharedPrefManager getInstance() {
        return sInstance;
    }

    public void saveToken(String token) {
        prefs.edit().putString(TOKEN, token).commit();
    }

    public String getToken() {
        if (token == null) {
            return prefs.getString(TOKEN, "");
        } else {
            return token;
        }
    }
}

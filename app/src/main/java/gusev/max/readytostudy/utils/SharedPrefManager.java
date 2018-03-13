package gusev.max.readytostudy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import static gusev.max.readytostudy.utils.Constants.NOTIFICATIONS_INTERVAL_CHOSEN;
import static gusev.max.readytostudy.utils.Constants.NOTIFICATIONS_PER_DAY;

public class SharedPrefManager {

    private static final String TOKEN = "TOKEN";

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

    public void setNotificationsIntervalChoosen(Boolean choosen) {
        prefs.edit().putBoolean(NOTIFICATIONS_INTERVAL_CHOSEN, choosen).commit();
    }

    public Boolean isNotificationIntervalChoosen() {
        return prefs.getBoolean(NOTIFICATIONS_INTERVAL_CHOSEN, false);
    }

    public void setNotificationInterval(int intervalConst) {
        prefs.edit().putInt(NOTIFICATIONS_PER_DAY, intervalConst).commit();
    }

    public int getNotificationsInterval() {
        return prefs.getInt(NOTIFICATIONS_PER_DAY, 4);
    }
}

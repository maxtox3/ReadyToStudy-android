package gusev.max.readytostudy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import gusev.max.readytostudy.App;

/**
 * Created by v on 21/01/2018.
 */

public class NetworkConnectionCheckUtil {

    public static boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager = (ConnectivityManager) App
            .getContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }
}

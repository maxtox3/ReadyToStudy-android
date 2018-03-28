package gusev.max.readytostudy;

import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

import gusev.max.readytostudy.di.DependencyInjection;
import gusev.max.readytostudy.utils.SharedPrefManager;

/**
 * Created by v on 13/01/2018.
 */

public class App extends Application {

    private static Context context;
    protected DependencyInjection dependencyInjection;

    @Override
    public void onCreate() {
        context = this;
        super.onCreate();
        SharedPrefManager.init(context);
        JodaTimeAndroid.init(this);
        dependencyInjection = new DependencyInjection();
    }

    public static DependencyInjection getDependencyInjection(Context context) {
        return ((App) context.getApplicationContext()).dependencyInjection;
    }

    public static Context getContext() {
        return context;
    }
}

package gusev.max.readytostudy.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by v on 12/03/2018.
 */

public class MyNotificationPublisher extends BroadcastReceiver {

    public static final String NOTIFICATION_ID = "notification_id";
    public static final String NOTIFICATION = "notification";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("onReceive: ", String.valueOf(System.currentTimeMillis()));

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
        if (notificationManager != null) {
            notificationManager.notify(notificationId, notification);
        }
    }
}


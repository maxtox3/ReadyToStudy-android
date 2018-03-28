package gusev.max.readytostudy.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import gusev.max.readytostudy.R;
import gusev.max.readytostudy.presentation.auth.AuthActivity;

/**
 * Created by v on 13/03/2018.
 */

public class NotificationBuilder {

    public static Notification getNotification(Context context, int notificationId) {
        NotificationCompat.Builder builder = getBuilder(context);

        Intent intent = new Intent(context, AuthActivity.class);
        PendingIntent activity = PendingIntent
                .getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        return builder.build();
    }

    public static NotificationCompat.Builder getBuilder(Context context) {
        return new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.content))
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
    }
}

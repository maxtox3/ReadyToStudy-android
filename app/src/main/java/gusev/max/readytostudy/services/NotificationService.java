package gusev.max.readytostudy.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Random;

import gusev.max.readytostudy.App;

import static gusev.max.readytostudy.utils.Constants.FOUR_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.NOTIFICATIONS_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.ONE_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.THREE_PER_DAY;
import static gusev.max.readytostudy.utils.Constants.TWO_PER_DAY;

/**
 * Created by v on 12/03/2018.
 */

public class NotificationService {

    private static final int[] FOUR_PARTS_INTERVAL = new int[]{10, 13, 16, 19};
    private static final int[] THREE_PARTS_INTERVAL = new int[]{12, 15, 18};
    private static final int[] TWO_PARTS_INTERVAL = new int[]{12, 18};
    private static final int[] ONE_PART_INTERVAL = new int[]{12};
    private static NotificationService instance = new NotificationService();

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }

        return instance;
    }

    public void createNextNotification() {
        long delay = 0L;
        DelayBuilder delayBuilder = new DelayBuilder();
        switch (PreferenceManager.getDefaultSharedPreferences(App.getContext())
                .getInt(NOTIFICATIONS_PER_DAY, 4)) {
            case FOUR_PER_DAY:
                delay = delayBuilder.createIntervals(FOUR_PARTS_INTERVAL);
                break;
            case THREE_PER_DAY:
                delay = delayBuilder.createIntervals(THREE_PARTS_INTERVAL);
                break;
            case TWO_PER_DAY:
                delay = delayBuilder.createIntervals(TWO_PARTS_INTERVAL);
                break;
            case ONE_PER_DAY:
                delay = delayBuilder.createIntervals(ONE_PART_INTERVAL);
                break;
            default:
                break;
        }

        if (delay != 0) {
            scheduleNotification(App.getContext(), delay, new Random().nextInt());
        }
    }

    private void scheduleNotification(Context context, long futureInMillis, int notificationId) {
        Notification notification = NotificationBuilder.getNotification(context, notificationId);

        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                notificationId,
                notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        }
    }

    private class DelayBuilder {

        private long createIntervals(int[] timePoints) {
            int min = timePoints[0];
            int max = timePoints[timePoints.length - 1];

            long delay = 0L;

            if (min == max) {
                delay = calculateNotificationForTomorrow(min);
            } else {
                if (isInMinVsMax(min, max)) {
                    delay = createForMinMax(min, max);
                } else {
                    for (int i = 0; i < timePoints.length - 1; i++) {
                        if (compareBetweenHours(timePoints[i], timePoints[i + 1])) {
                            delay = calculateNotificationForToday(timePoints[i + 1]);
                        }
                    }
                }
            }
            return delay;
        }

        private long createForMinMax(int min, int max) {
            long delay = 0L;
            if (compareNowHourVsMinHour(min)) {
                delay = calculateNotificationForToday(min);
            } else if (compareNowHourVsMaxHour(max)) {
                delay = calculateNotificationForTomorrow(min);
            }
            return delay;
        }

//    private void createFourIntervals() {
//        if (isInMinVsMax(10, 19)) {
//            if (compareNowHourVsMinHour(10)) {
//                calculateNotificationForToday(10);
//            } else if (compareNowHourVsMaxHour(19)) {
//                calculateNotificationForTomorrow(10);
//            }
//        } else if (compareBetweenHours(10, 13)) {
//            calculateNotificationForToday(13);
//        } else if (compareBetweenHours(13, 16)) {
//            calculateNotificationForToday(16);
//        } else if (compareBetweenHours(16, 19)) {
//            calculateNotificationForToday(19);
//        }
//    }

        private boolean isInMinVsMax(int min, int max) {
            return (min > nowInHours() || max < nowInHours());
        }

        private boolean compareNowHourVsMinHour(int min) {
            return (min > nowInHours());
        }

        private boolean compareNowHourVsMaxHour(int max) {
            return max < nowInHours();
        }

        private boolean compareBetweenHours(int min, int max) {
            return nowInHours() > min && nowInHours() < max;
        }

        private int nowInHours() {
            return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        }

        private DateTime getToday() {
            return DateTime.now();
        }

        private long calculateNotificationForToday(int hour) {
            return calculateForToday(hour).getMillis() - System.currentTimeMillis();
        }

        private DateTime calculateForToday(int hour) {
            return getToday().plusHours(hour - nowInHours());
        }

        private long calculateNotificationForTomorrow(int hour) {
            return calculateForTomorrow(hour).getMillis() - System.currentTimeMillis();
        }

        private DateTime calculateForTomorrow(int hour) {
            return DateTime.now().plusDays(1).plusHours(hour - nowInHours());
        }
    }
}

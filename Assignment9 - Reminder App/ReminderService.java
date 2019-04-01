package com.murach.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zbrinlee on 6/8/2017.
 */
public class ReminderService extends Service
{
   private Timer timer;


    @Override
    public void onCreate()
    {
        Log.d("Reminder App", "Service created");
        startTimer();
    }


    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d("Reminder App", "Binding not used");
        return null;
    }

    @Override
    public void onDestroy()
    {
        Log.d("Reminder App", "Service destroyed");
        stopTimer();
    }

    private void startTimer()
    {
        TimerTask task = new TimerTask()
        {

            @Override
            public void run()
            {
                Log.d("Reminder App", "Look into the distance. It's good for your eyes!");
                notification("Look into the distance. It’s good for your eyes.");
            }
        };

        timer = new Timer(true);
        int delay = 1000 * 5;
        int interval = 1000 * 10;   // 10 seconds for testing
        timer.schedule(task, delay, interval);
    }

    private void stopTimer()
    {
        if (timer != null)
        {
            timer.cancel();
        }
    }

    private void notification(String text)
    {
        // create the intent for the notification
        Intent notificationIntent = new Intent(this, ReminderActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // create the pending intent
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, notificationIntent, flags);

        // create the variables for the notification
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "Look into the distance. It’s good for your eyes.";
        CharSequence contentTitle = getText(R.string.app_name);
        CharSequence contentText = text;

        // create the notification and set its data
        Notification notification =
                new Notification.Builder(this)
                        .setSmallIcon(icon)
                        .setTicker(tickerText)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .build();

        // display the notification
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        final int NOTIFICATION_ID = 1;
        manager.notify(NOTIFICATION_ID, notification);
    }
}//end of class

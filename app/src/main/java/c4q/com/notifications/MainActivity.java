package c4q.com.notifications;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Setting a notification ID allows you to update the notification later on.
    private static final int NOTIFICATION_ID = 555;
    // Setting a notification channel allows the user to make choices about groups of notifications in later Android versions
    private static final String NOTIFICATION_CHANNEL = "C4Q Notifications";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchTestService();

        scheduleAlarm();

        Intent intent = new Intent(this, Main2Activity.class);


        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon2)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                // Hides the notification after its been selected
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    // ... call launchTestService() in the Activity onCreate()
    public void launchTestService() {
        Intent i = new Intent(this, MyNotificationService.class);
        startService(i);
    }

    //Set up recurring alarm for every half hour
    public void scheduleAlarm(){
        //Construction intent that will execute the alarm Receiver.
        Intent intent = new Intent(getApplicationContext(),MyAlarmReceiver.class);

        // Create a PendingIntent to be triggered when the alarm goes off

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this,MyAlarmReceiver.REQUEST_CODE, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        long firstMillis = System.currentTimeMillis();// alarm is set right away
        AlarmManager alarm =(AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // First parameter is the type: ELAPSED_ REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);



    }
}

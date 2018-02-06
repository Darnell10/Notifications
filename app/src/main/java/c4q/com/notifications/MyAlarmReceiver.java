package c4q.com.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by D on 1/28/18.
 */

public class MyAlarmReceiver extends BroadcastReceiver{

    public static final int REQUEST_CODE = 12345;
    public static final String Action ="nyc.c4q.notification.alarm";

    // Triggered by the Alarm periodically (starts the notification service)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,MyNotificationService.class);
        context.startService(i);
    }
}

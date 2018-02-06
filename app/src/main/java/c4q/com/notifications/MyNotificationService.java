package c4q.com.notifications;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by D on 1/28/18.
 */

public class MyNotificationService extends IntentService {

    // Used to name the worker thread, important only for debugging.
    private static final String SERVICE_NAME = "notification-service";

    // Must create a default constructor
    public MyNotificationService() {
        super(SERVICE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // This describes what will happen when service is triggered, i.e. show a notification
    }


}

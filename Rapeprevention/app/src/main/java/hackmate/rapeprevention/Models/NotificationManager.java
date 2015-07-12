package hackmate.rapeprevention.Models;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import hackmate.rapeprevention.MyApplication;
import hackmate.rapeprevention.R;
import hackmate.rapeprevention.ReactionActivity;

public class NotificationManager {
  public static void sendNotification() {
    Context context = MyApplication.getAppContext();
    NotificationCompat.Builder mBuilder =
        new NotificationCompat.Builder(context).setSmallIcon(R.drawable.logo)
            .setContentTitle("Check Time")
            .setContentText("Click to open reaction test.");
    // Creates an explicit intent for an Activity in your app
    Intent resultIntent = new Intent(context, ReactionActivity.class);

    // The stack builder object will contain an artificial back stack for the
    // started Activity.
    // This ensures that navigating backward from the Activity leads out of
    // your application to the Home screen.
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
    // Adds the back stack for the Intent (but not the Intent itself)
    stackBuilder.addParentStack(ReactionActivity.class);
    // Adds the Intent that starts the Activity to the top of the stack
    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent =
        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    mBuilder.setContentIntent(resultPendingIntent);
    android.app.NotificationManager mNotificationManager =
        (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    // mId allows you to update the notification later on.
    mNotificationManager.notify(0, mBuilder.build());
  }
}

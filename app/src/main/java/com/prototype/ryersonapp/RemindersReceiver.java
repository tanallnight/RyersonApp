package com.prototype.ryersonapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class RemindersReceiver extends BroadcastReceiver {

    private Bundle bundle;
    private String title, description;
    private int id = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        bundle = intent.getBundleExtra("TITLE_DESCRIPTION");
        title = bundle.getString("KEY_TITLE");
        description = bundle.getString("KEY_DESCRIPTION");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_reminder_white)
                .setContentTitle(title)
                .setContentText(description);

        Intent resultIntent = new Intent(context, RemindersActivity.class);

        if (Build.VERSION.SDK_INT > 15) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(RemindersActivity.class);
            stackBuilder.addNextIntent(resultIntent);

            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(pendingIntent);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, mBuilder.build());

    }
}

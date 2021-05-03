package com.sumitthorat.covidvaccinefinder.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.sumitthorat.covidvaccinefinder.R;

public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifySlot")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notification title")
                .setContentText("Notification text")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, builder.build());
    }
}

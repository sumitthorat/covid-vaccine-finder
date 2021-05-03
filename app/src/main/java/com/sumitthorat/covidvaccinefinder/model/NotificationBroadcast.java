package com.sumitthorat.covidvaccinefinder.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.sumitthorat.covidvaccinefinder.R;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.Session;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.VaccineSessionsModel;

import java.util.List;

public class NotificationBroadcast extends BroadcastReceiver {
    private final static String TAG = "NotificationBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        VaccineSessionsModel vaccineSessionsModel = new VaccineSessionsModel();

        Thread thread = new Thread() {
            @Override
            public void run() {
                int pincode = 110001;
                String date = "07-05-2021";
                List<Session> sessions = vaccineSessionsModel.fetchVaccineSessionsByPincodeAndDate(pincode, date);

                String notifText = sessions == null ? "0" : sessions.size() + " sessions at " + pincode + " on " + date;
                Log.i(TAG, notifText);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifySlot")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Vaccine sessions available")
                        .setContentText(notifText)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(200, builder.build());
            }
        };

        thread.start();

    }
}

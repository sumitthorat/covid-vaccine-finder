package com.sumitthorat.covidvaccinefinder.model;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.sumitthorat.covidvaccinefinder.R;
import com.sumitthorat.covidvaccinefinder.controller.AvailableSessionActivity;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.Session;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.VaccineSessionsModel;

import java.util.List;

import static com.sumitthorat.covidvaccinefinder.model.Constants.sharedPrefName;

public class NotificationBroadcast extends BroadcastReceiver {
    private final static String TAG = "NotificationBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        VaccineSessionsModel vaccineSessionsModel = new VaccineSessionsModel();

        Thread thread = new Thread() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
                int pincode = sharedPreferences.getInt(Constants.pincodeSharedPrefKey, -1);
                String date = sharedPreferences.getString(Constants.dateSharedPrefKey, "");
                int age = sharedPreferences.getInt(Constants.ageSharedPrefKey, -1);
                if (pincode == -1 || date.equals("") || age == -1) {
                    Log.e(TAG, "Error with pincode and date fetching from shared preference");
                    return;
                }

                List<Session> sessions = vaccineSessionsModel.fetchVaccineSessionsByPincodeDateAge(pincode, date, age);

                String notifText = sessions == null ? "0" : sessions.size() + " sessions at " + pincode + " on " + date;
                Log.i(TAG, notifText);

                Intent intent = new Intent(context, AvailableSessionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifySlot")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Vaccine sessions available")
                        .setContentText(notifText)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(200, builder.build());
            }
        };

        thread.start();

    }
}

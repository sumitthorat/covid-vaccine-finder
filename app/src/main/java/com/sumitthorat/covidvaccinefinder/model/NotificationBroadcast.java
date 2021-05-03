package com.sumitthorat.covidvaccinefinder.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.sumitthorat.covidvaccinefinder.R;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.Session;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.VaccineSessionsModel;

import java.util.List;

import static com.sumitthorat.covidvaccinefinder.model.Constants.pincodeSharedPrefKey;
import static com.sumitthorat.covidvaccinefinder.model.Constants.dateSharedPrefKey;
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
                int pincode = sharedPreferences.getInt(pincodeSharedPrefKey, -1);
                String date = sharedPreferences.getString(dateSharedPrefKey, "");
                if (pincode == -1 || date.equals("")) {
                    Log.e(TAG, "Error with pincode and date fetchinf from shared preference");
                    return;
                }

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

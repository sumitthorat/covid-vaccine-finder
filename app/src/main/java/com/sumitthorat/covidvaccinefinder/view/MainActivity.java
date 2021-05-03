package com.sumitthorat.covidvaccinefinder.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sumitthorat.covidvaccinefinder.R;

public class MainActivity extends AppCompatActivity {

    Button btnStartFinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindControls();
        setListeners();
        createNotificationChannel();
    }

    private void bindControls() {
        btnStartFinding = findViewById(R.id.btn_start_finding);
    }

    private void setListeners() {
        btnStartFinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFinding();
            }
        });
    }

    void startFinding() {
        Toast.makeText(this, "Start finding", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, NotificationBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();

        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + 10000, pendingIntent);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CovidVaccineFinder";
            String description = "Channel for notifications";

            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("notifySlot", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}
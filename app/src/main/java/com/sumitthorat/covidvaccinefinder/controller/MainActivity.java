package com.sumitthorat.covidvaccinefinder.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.sumitthorat.covidvaccinefinder.R;
import com.sumitthorat.covidvaccinefinder.model.ApiInterface;
import com.sumitthorat.covidvaccinefinder.model.ApiUtils;
import com.sumitthorat.covidvaccinefinder.model.Constants;
import com.sumitthorat.covidvaccinefinder.model.NotificationBroadcast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnStartFinding, btnStopFinding;
    TextInputLayout etPincode, etDate, etCheckingInterval;
    ApiInterface apiInterface;
    ConstraintLayout mainActivityCL;
    AlarmManager alarmManager;
    PendingIntent findSessionsPendingIntent;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiUtils.getApiInterface();

        bindControls();
        setListeners();
        createNotificationChannel();
    }

    private void bindControls() {
        mainActivityCL = findViewById(R.id.main_activity_cl);
        btnStartFinding = findViewById(R.id.btn_start_finding);
        btnStopFinding = findViewById(R.id.btn_stop_finding);
        etPincode = findViewById(R.id.et_pincode);
        etDate = findViewById(R.id.et_date);
        etCheckingInterval = findViewById(R.id.et_checking_interval);
    }

    private void setListeners() {
        btnStartFinding.setOnClickListener(v -> startFinding());
        btnStopFinding.setOnClickListener(v -> stopFinding());
    }

    @SuppressLint("SimpleDateFormat")
    void startFinding() {
        Toast.makeText(this, "Start finding", Toast.LENGTH_SHORT).show();

        int pincode = 0;
        try {
            pincode = Integer.parseInt(etPincode.getEditText().getText().toString());
        } catch (Exception e) {
            SnackbarUtil.showErrorSnackbar(mainActivityCL, "Please check pincode");
            Log.e(TAG, "Exception: ", e);
            return;
        }

        Date date;
        String dateAsStr;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(etDate.getEditText().getText().toString());
            if (date != null) {
                dateAsStr = etDate.getEditText().getText().toString();
            } else {
                SnackbarUtil.showErrorSnackbar(mainActivityCL, "Please check date");
                return;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: ", e);
            return;
        }

        int checkingInterval = 1;
        try {
            checkingInterval = Integer.parseInt(etCheckingInterval.getEditText().getText().toString());
        } catch (Exception e) {
            SnackbarUtil.showErrorSnackbar(mainActivityCL, "Please check interval");
            Log.e(TAG, "Exception: ", e);
            return;
        }

        // Save pincode and date string to persistent store
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.sharedPrefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.pincodeSharedPrefKey, pincode);
        editor.putString(Constants.dateSharedPrefKey, dateAsStr);
        editor.apply();


        Intent intent = new Intent(MainActivity.this, NotificationBroadcast.class);
        findSessionsPendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonClick = System.currentTimeMillis();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeAtButtonClick, checkingInterval * 60 * 1000, findSessionsPendingIntent);

    }

    void stopFinding() {
        Toast.makeText(this, "Stop finding", Toast.LENGTH_SHORT).show();
        if (alarmManager != null) {
            alarmManager.cancel(findSessionsPendingIntent);
        }
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
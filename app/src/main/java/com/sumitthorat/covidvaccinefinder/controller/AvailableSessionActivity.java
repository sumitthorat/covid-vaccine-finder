package com.sumitthorat.covidvaccinefinder.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;
import com.sumitthorat.covidvaccinefinder.R;
import com.sumitthorat.covidvaccinefinder.model.Constants;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.Session;
import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.VaccineSessionsModel;

import java.util.List;

import static com.sumitthorat.covidvaccinefinder.model.Constants.sharedPrefName;

public class AvailableSessionActivity extends AppCompatActivity {

    public final static String TAG = "AvailableSessionsActivi";

    VaccineSessionsModel vaccineSessionsModel;
    List<Session> sessions;
    LinearLayout llAvailableSessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_session);
        Log.i(TAG, "Inside available sessions");
        vaccineSessionsModel = new VaccineSessionsModel();
        bindControls();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSessionFromAPI();
    }

    private void fetchSessionFromAPI() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
                int pincode = sharedPreferences.getInt(Constants.pincodeSharedPrefKey, -1);
                String date = sharedPreferences.getString(Constants.dateSharedPrefKey, "");
                int age = sharedPreferences.getInt(Constants.ageSharedPrefKey, -1);
                if (pincode == -1 || date.equals("") || age == -1) {
                    Log.e(TAG, "Error with pincode and date fetching from shared preference");
                    return;
                }

                sessions = vaccineSessionsModel.fetchVaccineSessionsByPincodeDateAge(pincode, date, age);
                updateSessionOnUI();

            }
        };

        thread.start();
    }

    private void bindControls() {
        llAvailableSessions = findViewById(R.id.ll_available_sessions);
    }

    private void updateSessionOnUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                populateSessionsCards();
            }
        });
    }

    private void populateSessionsCards() {
        llAvailableSessions.removeAllViews();

        for (Session session: sessions) {
            View cvSession = getLayoutInflater().inflate(R.layout.session_material_card, null);

            MaterialTextView tvSessionCenterName = cvSession.findViewById(R.id.tv_session_center_name);
            MaterialTextView tvSessionCenterDistrict = cvSession.findViewById(R.id.tv_session_center_district);
            MaterialTextView tvSessionFee = cvSession.findViewById(R.id.tv_session_fee);
            MaterialTextView tvSessionVaccine = cvSession.findViewById(R.id.tv_session_vaccine_type);
            MaterialTextView tvMinAge = cvSession.findViewById(R.id.tv_session_min_age);
            MaterialTextView tvSessionAvailCapacity = cvSession.findViewById(R.id.tv_session_available_capacity);
            MaterialTextView tvSessionSlots = cvSession.findViewById(R.id.tv_session_slots);

            String tvSessionCenterNameText =  "Center Name: " + session.getName();
            tvSessionCenterName.setText(tvSessionCenterNameText);

            String tvSessionCenterDistrictText =  "District Name: " + session.getDistrictName();
            tvSessionCenterDistrict.setText(tvSessionCenterDistrictText);

            String tvSessionFeeText =  "Fees: " + session.getFee();
            tvSessionFee.setText(tvSessionFeeText);

            String tvMinAgeText =  "Minimum Age Limit " + session.getMinAgeLimit();
            tvMinAge.setText(tvMinAgeText);

            String tvSessionVaccineText =  "Available Capacity:" + session.getAvailableCapacity();
            tvSessionAvailCapacity.setText(tvSessionVaccineText);

            String tvSessionAvailCapacityText =  "Vaccine: " + session.getVaccine();
            tvSessionVaccine.setText(tvSessionAvailCapacityText);

            String tvSessionSlotsText =  "Slots: ";
            List<String> slots = session.getSlots();
            for (int i = 0; i < slots.size() - 1; ++i)
            for (String slot: session.getSlots()) {
                tvSessionSlotsText += slot + ", ";
            }
            tvSessionSlotsText += slots.get(slots.size() - 1);

            tvSessionSlots.setText(tvSessionSlotsText);

            llAvailableSessions.addView(cvSession);
        }
    }
}
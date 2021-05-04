package com.sumitthorat.covidvaccinefinder.model.vaccinesessions;

import android.util.Log;

import com.sumitthorat.covidvaccinefinder.model.ApiInterface;
import com.sumitthorat.covidvaccinefinder.model.ApiUtils;

import java.util.ArrayList;
import java.util.List;

public class VaccineSessionsModel {
    private final static String TAG = "VaccineSessionsModel";
    ApiInterface apiInterface;

    public VaccineSessionsModel() {
        apiInterface = ApiUtils.getApiInterface();
    }

    public List<Session> fetchVaccineSessionsByPincodeDateAge(int pincode, String date, int age) {
        VaccineSessionsJSONResponse response = null;

        try {
            response = apiInterface.getVaccineSessionsByPincodeAndDate(pincode, date).execute().body();
        } catch (Exception e) {
            Log.e(TAG, "Exception: ", e);
        }


        List<Session> filteredSessions = new ArrayList<>();

        for (Session session: response.getSessions()) {
            if (age >= session.getMinAgeLimit()) {
                filteredSessions.add(session);
            }
        }


        return response == null ? null : filteredSessions;
    }

}

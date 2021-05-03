package com.sumitthorat.covidvaccinefinder.model.vaccinesessions;

import android.util.Log;

import com.sumitthorat.covidvaccinefinder.model.ApiInterface;
import com.sumitthorat.covidvaccinefinder.model.ApiUtils;

import java.util.List;

public class VaccineSessionsModel {
    private final static String TAG = "VaccineSessionsModel";
    ApiInterface apiInterface;

    public VaccineSessionsModel() {
        apiInterface = ApiUtils.getApiInterface();
    }

    public List<Session> fetchVaccineSessionsByPincodeAndDate(int pincode, String date) {
        VaccineSessionsJSONResponse response = null;

        try {
            response = apiInterface.getVaccineSessionsByPincodeAndDate(pincode, date).execute().body();
        } catch (Exception e) {
            Log.e(TAG, "Exception: ", e);
        }

        return response == null ? null : response.getSessions();
    }
}

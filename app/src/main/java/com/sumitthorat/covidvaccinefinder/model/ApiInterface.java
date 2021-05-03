package com.sumitthorat.covidvaccinefinder.model;

import com.sumitthorat.covidvaccinefinder.model.vaccinesessions.VaccineSessionsJSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({
            "accept: application/json",
            "Accept-Language: hi_IN"
    })
    @GET("appointment/sessions/public/findByPin")
    Call<VaccineSessionsJSONResponse> getVaccineSessionsByPincodeAndDate(@Query("pincode") int pincode, @Query("date") String date); // date = dd-mm-yyyy
}

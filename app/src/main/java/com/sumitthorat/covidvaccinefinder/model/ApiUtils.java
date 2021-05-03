package com.sumitthorat.covidvaccinefinder.model;

/**
 * Class to instantiate Api Interface user
 */
public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "https://cdn-api.co-vin.in/api/v2/";


    public static ApiInterface getApiInterface() {

        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}

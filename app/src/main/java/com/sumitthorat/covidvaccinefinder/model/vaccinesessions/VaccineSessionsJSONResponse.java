
package com.sumitthorat.covidvaccinefinder.model.vaccinesessions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VaccineSessionsJSONResponse {

    @SerializedName("sessions")
    @Expose
    private List<Session> sessions = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VaccineSessionsJSONResponse() {
    }

    /**
     * 
     * @param sessions
     */
    public VaccineSessionsJSONResponse(List<Session> sessions) {
        super();
        this.sessions = sessions;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

}

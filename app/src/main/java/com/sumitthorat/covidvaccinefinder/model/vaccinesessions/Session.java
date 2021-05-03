
package com.sumitthorat.covidvaccinefinder.model.vaccinesessions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Session {

    @SerializedName("center_id")
    @Expose
    private Integer centerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_l")
    @Expose
    private String nameL;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("state_name_l")
    @Expose
    private String stateNameL;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("district_name_l")
    @Expose
    private String districtNameL;
    @SerializedName("block_name")
    @Expose
    private String blockName;
    @SerializedName("block_name_l")
    @Expose
    private String blockNameL;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("long")
    @Expose
    private Double _long;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("fee_type")
    @Expose
    private String feeType;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("available_capacity")
    @Expose
    private Integer availableCapacity;
    @SerializedName("min_age_limit")
    @Expose
    private Integer minAgeLimit;
    @SerializedName("vaccine")
    @Expose
    private String vaccine;
    @SerializedName("slots")
    @Expose
    private List<String> slots = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Session() {
    }

    /**
     * 
     * @param date
     * @param centerId
     * @param pincode
     * @param availableCapacity
     * @param minAgeLimit
     * @param blockNameL
     * @param districtName
     * @param districtNameL
     * @param blockName
     * @param stateNameL
     * @param fee
     * @param sessionId
     * @param feeType
     * @param vaccine
     * @param slots
     * @param stateName
     * @param _long
     * @param name
     * @param nameL
     * @param from
     * @param to
     * @param lat
     */
    public Session(Integer centerId, String name, String nameL, String stateName, String stateNameL, String districtName, String districtNameL, String blockName, String blockNameL, String pincode, Double lat, Double _long, String from, String to, String feeType, String fee, String sessionId, String date, Integer availableCapacity, Integer minAgeLimit, String vaccine, List<String> slots) {
        super();
        this.centerId = centerId;
        this.name = name;
        this.nameL = nameL;
        this.stateName = stateName;
        this.stateNameL = stateNameL;
        this.districtName = districtName;
        this.districtNameL = districtNameL;
        this.blockName = blockName;
        this.blockNameL = blockNameL;
        this.pincode = pincode;
        this.lat = lat;
        this._long = _long;
        this.from = from;
        this.to = to;
        this.feeType = feeType;
        this.fee = fee;
        this.sessionId = sessionId;
        this.date = date;
        this.availableCapacity = availableCapacity;
        this.minAgeLimit = minAgeLimit;
        this.vaccine = vaccine;
        this.slots = slots;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameL() {
        return nameL;
    }

    public void setNameL(String nameL) {
        this.nameL = nameL;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateNameL() {
        return stateNameL;
    }

    public void setStateNameL(String stateNameL) {
        this.stateNameL = stateNameL;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictNameL() {
        return districtNameL;
    }

    public void setDistrictNameL(String districtNameL) {
        this.districtNameL = districtNameL;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockNameL() {
        return blockNameL;
    }

    public void setBlockNameL(String blockNameL) {
        this.blockNameL = blockNameL;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLong() {
        return _long;
    }

    public void setLong(Double _long) {
        this._long = _long;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(Integer availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Integer getMinAgeLimit() {
        return minAgeLimit;
    }

    public void setMinAgeLimit(Integer minAgeLimit) {
        this.minAgeLimit = minAgeLimit;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public List<String> getSlots() {
        return slots;
    }

    public void setSlots(List<String> slots) {
        this.slots = slots;
    }

}

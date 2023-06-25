package com.etraveli.model;

public class ExceededTemperatureUserView {

    //This view contains logic to filter only rows where Temperature has exceeded the user preference.
    //Refer schema.sql inside \resources folder View name : EXCEEDED_TEMP_V

    private String city;
    private String state;
    private Long userId;
    private Long notifyInMins;
    private String isSmsActive;
    private String isMailActive;
    private String isAppNotifyActive;
    private Long threshold;
    private Long tempC;
    private Long tempF;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNotifyInMins() {
        return notifyInMins;
    }

    public void setNotifyInMins(Long notifyInMins) {
        this.notifyInMins = notifyInMins;
    }

    public String getIsSmsActive() {
        return isSmsActive;
    }

    public void setIsSmsActive(String isSmsActive) {
        this.isSmsActive = isSmsActive;
    }

    public String getIsMailActive() {
        return isMailActive;
    }

    public void setIsMailActive(String isMailActive) {
        this.isMailActive = isMailActive;
    }

    public String getIsAppNotifyActive() {
        return isAppNotifyActive;
    }

    public void setIsAppNotifyActive(String isAppNotifyActive) {
        this.isAppNotifyActive = isAppNotifyActive;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    public Long getTempC() {
        return tempC;
    }

    public void setTempC(Long tempC) {
        this.tempC = tempC;
    }

    public Long getTempF() {
        return tempF;
    }

    public void setTempF(Long tempF) {
        this.tempF = tempF;
    }
}

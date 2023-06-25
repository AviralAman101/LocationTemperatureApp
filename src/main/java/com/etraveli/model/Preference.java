package com.etraveli.model;

public class Preference extends PreferenceID{

    private long notifyCycleMins;
    private String isSmsActive;
    private String isMailActive;
    private String isAppNotifyActive;
    private Long thresholdInC;

    public long getNotifyCycleMins() {
        return notifyCycleMins;
    }

    public void setNotifyCycleMins(long notifyCycleMins) {
        this.notifyCycleMins = notifyCycleMins;
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

    public Long getThresholdInC() {
        return thresholdInC;
    }

    public void setThresholdInC(Long thresholdInC) {
        this.thresholdInC = thresholdInC;
    }
}

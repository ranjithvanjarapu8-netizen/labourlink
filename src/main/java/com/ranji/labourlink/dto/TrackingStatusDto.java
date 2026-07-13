package com.ranji.labourlink.dto;

public class TrackingStatusDto {

    private Boolean trackingEnabled;

    public TrackingStatusDto() {
    }

    public TrackingStatusDto(Boolean trackingEnabled) {
        this.trackingEnabled = trackingEnabled;
    }

    public Boolean getTrackingEnabled() {
        return trackingEnabled;
    }

    public void setTrackingEnabled(Boolean trackingEnabled) {
        this.trackingEnabled = trackingEnabled;
    }
}

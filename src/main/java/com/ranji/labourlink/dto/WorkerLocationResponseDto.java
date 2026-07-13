package com.ranji.labourlink.dto;

import java.time.LocalDateTime;

public class WorkerLocationResponseDto {

    private Double latitude;
    private Double longitude;
    private LocalDateTime lastUpdated;
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

    // Getters and Setters
    
}
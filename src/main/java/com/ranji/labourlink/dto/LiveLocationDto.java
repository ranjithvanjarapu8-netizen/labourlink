package com.ranji.labourlink.dto;

import java.time.LocalDateTime;

public class LiveLocationDto {

    private Long requestId;

    private Long workerId;

    private Double workerLatitude;

    private Double workerLongitude;

    private Double workLatitude;

    private Double workLongitude;

    private String status;

    private LocalDateTime lastUpdated;

    // Getters & Setters

    public Double getWorkerLatitude() {
        return workerLatitude;
    }

    public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setWorkerLatitude(Double workerLatitude) {
        this.workerLatitude = workerLatitude;
    }

    public Double getWorkerLongitude() {
        return workerLongitude;
    }

    public void setWorkerLongitude(Double workerLongitude) {
        this.workerLongitude = workerLongitude;
    }

    public Double getWorkLatitude() {
        return workLatitude;
    }

    public void setWorkLatitude(Double workLatitude) {
        this.workLatitude = workLatitude;
    }

    public Double getWorkLongitude() {
        return workLongitude;
    }

    public void setWorkLongitude(Double workLongitude) {
        this.workLongitude = workLongitude;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
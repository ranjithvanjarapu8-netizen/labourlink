package com.ranji.labourlink.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ranji.labourlink.Model.RequestStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AcceptedRequestDto {

    private Long requestId;

    private String ownerName;

    private String ownerPhone;

    private String workerName;

    private String workerPhone;

    private String profession;

    private String title;

    private String description;

    private Double distance;

    private String address;

    private Double latitude;

    private Double longitude;

    private LocalDate workDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private RequestStatusEnum status;
    public AcceptedRequestDto() {
    	
    }
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerPhone() {
		return ownerPhone;
	}
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerPhone() {
		return workerPhone;
	}
	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
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
	public LocalDate getWorkDate() {
		return workDate;
	}
	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public RequestStatusEnum getStatus() {
		return status;
	}
	public void setStatus(RequestStatusEnum status) {
		this.status = status;
	}
    

}
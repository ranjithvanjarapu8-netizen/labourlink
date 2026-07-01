
package com.ranji.labourlink.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ranji.labourlink.Model.RequestStatusEnum;

public class OwnerRequestDto {

    private Long requestId;

    private Long workerId;

    private String workerName;

    private String phoneNumber;

    private String profession;

    private String title;

    private String address;

    private LocalDate workDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status;
    
    private String profilePhoto;

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public OwnerRequestDto() {
    }

	public OwnerRequestDto(
	        Long requestId,
	        Long workerId,
	        String workerName,
	        String phoneNumber,
	        String profilePhoto,
	        String profession,
	        String title,
	        String address,
	        LocalDate workDate,
	        LocalTime startTime,
	        LocalTime endTime,
	        RequestStatusEnum status) {

	    this.requestId = requestId;
	    this.workerId = workerId;
	    this.workerName = workerName;
	    this.phoneNumber = phoneNumber;
	    this.profilePhoto = profilePhoto;
	    this.profession = profession;
	    this.title = title;
	    this.address = address;
	    this.workDate = workDate;
	    this.startTime = startTime;
	    this.endTime = endTime;
	    this.status = status.name();
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

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    // Generate Getters & Setters
}
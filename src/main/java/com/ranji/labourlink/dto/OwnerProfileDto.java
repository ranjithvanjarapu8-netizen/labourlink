package com.ranji.labourlink.dto;

import java.time.LocalDateTime;

public class OwnerProfileDto {

    private String name;

    private String phoneNumber;

    private LocalDateTime joinedDate;

    private Integer totalRequests;

    private Integer upcomingRequests;

    private Integer pastRequests;

	public OwnerProfileDto() {
		
	}
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(LocalDateTime joinedDate) {
		this.joinedDate = joinedDate;
	}

	public Integer getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(Integer totalRequests) {
		this.totalRequests = totalRequests;
	}

	public Integer getUpcomingRequests() {
		return upcomingRequests;
	}

	public void setUpcomingRequests(Integer upcomingRequests) {
		this.upcomingRequests = upcomingRequests;
	}

	public Integer getPastRequests() {
		return pastRequests;
	}

	public void setPastRequests(Integer pastRequests) {
		this.pastRequests = pastRequests;
	}

    // Getters & Setters
}

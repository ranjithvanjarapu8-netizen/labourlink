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
public class IncomingRequestDto {

    private Long requestId;

    private String ownerName;

    private String profession;

    private String title;

    private String description;

    private Double distance;

    private LocalDate workDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private RequestStatusEnum status;
    
    public IncomingRequestDto() {
    	
    }
    

	public IncomingRequestDto(Long requestId, String ownerName, String profession, String title, String description,
			Double distance, LocalDate workDate, LocalTime startTime, LocalTime endTime, RequestStatusEnum status) {
		super();
		this.requestId = requestId;
		this.ownerName = ownerName;
		this.profession = profession;
		this.title = title;
		this.description = description;
		this.distance = distance;
		this.workDate = workDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
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
package com.ranji.labourlink.dto;

import java.util.List;

public class WorkerCardDto {

    private Long workerId;

    private String name;

    private List<String> profession;

    private Integer experience;

    private Double rating;

    private Integer totalJobs;

    private String profilePhoto;

    private Double distance;
    
    private Integer dailyWage;
    public WorkerCardDto() {
    	
    }


    public WorkerCardDto(Long workerId,
	            String name,
	            List<String> profession,
	            Integer experience,
	            Double rating,
	            Integer totalJobs,
	            String profilePhoto,
	            Double distance,
	            Integer dailyWage) {
	
	this.workerId = workerId;
	this.name = name;
	this.profession = profession;
	this.experience = experience;
	this.rating = rating;
	this.totalJobs = totalJobs;
	this.profilePhoto = profilePhoto;
	this.distance = distance;
	this.dailyWage = dailyWage;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<String> getProfession() {
		return profession;
	}


	public void setProfession(List<String> profession) {
		this.profession = profession;
	}


	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getTotalJobs() {
		return totalJobs;
	}

	public void setTotalJobs(Integer totalJobs) {
		this.totalJobs = totalJobs;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Integer getDailyWage() {
	    return dailyWage;
	}

	public void setDailyWage(Integer dailyWage) {
	    this.dailyWage = dailyWage;
	}
}

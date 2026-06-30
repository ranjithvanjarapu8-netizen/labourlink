package com.ranji.labourlink.dto;

import java.time.LocalDate;
import java.util.List;

public class OwnrWrkrProfileDto {
	private String name;


    private List<String> professions;

    private Integer experience;

    private Double rating;

    private Integer totalJobs;

    private Boolean available;

    private String city;

    private String district;

    private String state;

    private String profilePhoto;

    private String description;

    private String languages;
    private LocalDate createdAt;
    
    public OwnrWrkrProfileDto() {
    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getProfessions() {
		return professions;
	}

	public void setProfessions(List<String> professions) {
		this.professions = professions;
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

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

}

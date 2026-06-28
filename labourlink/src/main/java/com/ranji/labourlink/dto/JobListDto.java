package com.ranji.labourlink.dto;

import java.time.LocalDate;

public class JobListDto {

    private Long jobId;

    private String profession;

    private String title;

    private Double wage;

    private String address;

    private LocalDate workDate;

    public JobListDto() {
    }

    public JobListDto(Long jobId, String profession, String title, Double wage, String address, LocalDate workDate) {
		super();
		this.jobId = jobId;
		this.profession = profession;
		this.title = title;
		this.wage = wage;
		this.address = address;
		this.workDate = workDate;
	}

	public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
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

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
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
}
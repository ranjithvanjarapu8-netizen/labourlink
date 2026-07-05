package com.ranji.labourlink.dto;

import java.util.List;

import com.ranji.labourlink.Model.WrkWage;

public class ProfessionWageDto {

    private String profession;

    private Integer dailyWage;

    private List<WorkDto> works;
    
    public ProfessionWageDto() {
    	
    }

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Integer getDailyWage() {
		return dailyWage;
	}

	public void setDailyWage(Integer dailyWage) {
		this.dailyWage = dailyWage;
	}

	public List<WorkDto> getWorks() {
		return works;
	}

	public void setWorks(List<WorkDto> works) {
		this.works = works;
	}
 

    // Getters & Setters
}

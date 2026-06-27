package com.ranji.labourlink.dto;

import lombok.Data;

@Data
public class PhoneDto {
	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}

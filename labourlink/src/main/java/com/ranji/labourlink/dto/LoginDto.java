package com.ranji.labourlink.dto;

import lombok.Data;

@Data
public class LoginDto {
	private String phoneNumber;
    private String password;
    public LoginDto() {
    	
    }
	public LoginDto(String phoneNumber, String password) {
		super();
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDto [phoneNumber=" + phoneNumber + ", password=" + password + "]";
	}
    
}

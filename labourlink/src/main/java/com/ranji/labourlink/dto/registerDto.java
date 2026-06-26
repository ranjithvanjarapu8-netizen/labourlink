package com.ranji.labourlink.dto;

import lombok.Data;

@Data
public class registerDto {
	private String name;
	private String phoneNumber;
	private String password;
	private String check;
	public registerDto(String name, String phoneNumber, String password, String check) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.check = check;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	@Override
	public String toString() {
		return "registerDto [name=" + name + ", phoneNumber=" + phoneNumber + ", password=" + password + ", check="
				+ check + "]";
	}
	
}

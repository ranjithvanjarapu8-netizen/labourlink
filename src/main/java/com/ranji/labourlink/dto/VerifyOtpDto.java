package com.ranji.labourlink.dto;

public class VerifyOtpDto {

    private String phoneNumber;
    private String otp;
    private String password;
    private String name;
    public VerifyOtpDto() {
    }

    public VerifyOtpDto(String password, String name) {
		super();
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
        this.otp = otp;
	}


    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
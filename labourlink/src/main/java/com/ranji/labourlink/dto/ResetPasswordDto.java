package com.ranji.labourlink.dto;

public class ResetPasswordDto {

    private String phoneNumber;
    private String newPassword;

    public ResetPasswordDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
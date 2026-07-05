package com.ranji.labourlink.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.dto.ForgotPasswordOtpDto;
import com.ranji.labourlink.dto.PhoneDto;
import com.ranji.labourlink.dto.ResetPasswordDto;
import com.ranji.labourlink.dto.VerifyOtpDto;
import com.twilio.rest.verify.v2.service.VerificationCheck;

@Service
public class ForgotPasswordService {
	
	@Value("${twilio.verify.service.sid}")
	private String verifySid;
	
	@Autowired
	private UserLoginRepo userRepo;
	
	@Autowired
	private otpVerifyServ otpServ;
	

    public ResponseEntity<String> sendOtp(PhoneDto dto) {

    	if (userRepo.findByPhoneNumber(dto.getPhoneNumber()).isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        return otpServ.OtpSend(dto);
    }

    public ResponseEntity<String> verifyForgotPasswordOtp(ForgotPasswordOtpDto dto) {

        System.out.println("Phone sent to Twilio: " + dto.getPhoneNumber());
        System.out.println("Verify SID: " + verifySid);

        VerificationCheck verificationCheck =
                VerificationCheck.creator(verifySid)
                        .setTo(dto.getPhoneNumber())
                        .setCode(dto.getOtp())
                        .create();

        if (!"approved".equalsIgnoreCase(verificationCheck.getStatus())) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        // Check if the user exists
        User user = userRepo.findByphno(dto.getPhoneNumber());

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok("OTP Verified Successfully");
    }

    public ResponseEntity<String> resetPassword(ResetPasswordDto dto) {

        User user = userRepo.findByphno(dto.getPhoneNumber());

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        user.setPassword(dto.getNewPassword());

        userRepo.save(user);

        return ResponseEntity.ok("Password reset successfully");
    }

}
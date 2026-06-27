package com.ranji.labourlink.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.OtpVerification;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.OtpRepo;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.dto.CompleteRegistrationDto;
import com.ranji.labourlink.dto.PhoneDto;
import com.ranji.labourlink.dto.VerifyOtpDto;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

@Service
public class otpVerifyServ {
	
	@Value("${twilio.verify.service.sid}")
	private String verifySid;
	
	@Autowired
	public OtpRepo otprep;

	@Autowired
	public UserLoginRepo logrep;

	public ResponseEntity<String> verifyOtp(VerifyOtpDto dto) {
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

	    // Check if already registered
	    if (logrep.findByphno(dto.getPhoneNumber()) != null) {
	        return ResponseEntity.badRequest().body("Phone number already registered");
	    }

	    // Create account
	    User user = new User();
	    user.setName(dto.getName());
	    user.setPhoneNumber(dto.getPhoneNumber());
	    user.setPassword(dto.getPassword());
	    user.setCreatedAt(LocalDateTime.now());

	    logrep.save(user);

	    return ResponseEntity.ok("Account Created Successfully");
	}
	
	public ResponseEntity<String> sendOtp(PhoneDto dto) {
		String s = dto.getPhoneNumber();
		System.out.println("Phone sent to Twilio: " + dto.getPhoneNumber());
		User cust = logrep.findByphno(s.substring(s.length()-10));
		 if (cust != null) {
		        return ResponseEntity.status(HttpStatus.CONFLICT)
		                .body("Account with this phone number already exists");
		    }
		System.out.println("Verify SID: " + verifySid);
	    Verification.creator(
	            verifySid,
	            dto.getPhoneNumber(),
	            "sms")
	            .create();
	    
	    return ResponseEntity.ok("OTP Sent Successfully");
	}
}

package com.ranji.labourlink.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.OtpVerification;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.OtpRepo;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Security.JwtUtil;
import com.ranji.labourlink.dto.LoginDto;
import com.ranji.labourlink.dto.LoginResponse;
import com.ranji.labourlink.dto.registerDto;

@Service
public class UserLoginServ {
	@Autowired
	public UserLoginRepo logrep; 
	
	@Autowired
	public OtpRepo otprep;
	
	public ResponseEntity<LoginResponse> login(LoginDto log) {

	    User cust = logrep.findByphno(log.getPhoneNumber());

	    if (cust == null) {
	        return new ResponseEntity<>(
	                new LoginResponse("No User Found", null),
	                HttpStatus.BAD_REQUEST
	        );
	    }

	    if (!cust.getPassword().equals(log.getPassword())) {
	        return new ResponseEntity<>(
	                new LoginResponse("Incorrect Password", null),
	                HttpStatus.BAD_REQUEST
	        );
	    }

	    String token = JwtUtil.generateToken(cust.getPhoneNumber());

	    return new ResponseEntity<>(
	            new LoginResponse("Successful Login", token),
	            HttpStatus.OK
	    );
	}
	public ResponseEntity<String> register(registerDto cust) {

	    // Check phone number already exists
	    User customer = logrep.findByphno(cust.getPhoneNumber());

	    if (customer != null) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Phone number already registered");
	    }

	    return ResponseEntity.ok("OTP_REQUIRED");
	}
}

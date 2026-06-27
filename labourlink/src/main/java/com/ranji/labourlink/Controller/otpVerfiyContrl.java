package com.ranji.labourlink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranji.labourlink.Service.otpVerifyServ;
import com.ranji.labourlink.dto.CompleteRegistrationDto;
import com.ranji.labourlink.dto.PhoneDto;
import com.ranji.labourlink.dto.VerifyOtpDto;

@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/otp")
public class otpVerfiyContrl {
	@Autowired
	public otpVerifyServ otpserv;
	
	
	@PostMapping("/verify-otp")
	public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpDto dto){
	    return otpserv.verifyOtp(dto);
	}
	
	@PostMapping("/send-otp")
	public ResponseEntity<String> sendOtp(@RequestBody PhoneDto dto){

	    return otpserv.sendOtp(dto);

	}
}

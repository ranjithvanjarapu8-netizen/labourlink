package com.ranji.labourlink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ranji.labourlink.Service.ForgotPasswordService;
import com.ranji.labourlink.dto.ForgotPasswordOtpDto;
import com.ranji.labourlink.dto.PhoneDto;
import com.ranji.labourlink.dto.ResetPasswordDto;
import com.ranji.labourlink.dto.VerifyOtpDto;

@CrossOrigin(origins = {
        "http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"
})
@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody PhoneDto dto) {
        return forgotPasswordService.sendOtp(dto);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody ForgotPasswordOtpDto dto) {
        return forgotPasswordService.verifyForgotPasswordOtp(dto);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto dto) {
        return forgotPasswordService.resetPassword(dto);
    }
}
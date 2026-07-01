package com.ranji.labourlink.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Service.OwnerService;
import com.ranji.labourlink.dto.OwnerProfileDto;
import com.ranji.labourlink.dto.OwnerRequestsResponse;

@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private UserLoginRepo userRepo;

    @GetMapping("/requests")
    public ResponseEntity<OwnerRequestsResponse> getOwnerRequests(
            Authentication authentication) {

        String phone = authentication.getName();

        User owner = userRepo.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(ownerService.getOwnerRequests(owner));
    }
    
    @GetMapping("/profile")
    public ResponseEntity<OwnerProfileDto> getOwnerProfile(
            Authentication authentication) {

        return ResponseEntity.ok(
                ownerService.getOwnerProfile(authentication.getName()));
    }
}


package com.ranji.labourlink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranji.labourlink.Service.WrkrRatingServ;
import com.ranji.labourlink.dto.WorkerRatingDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ownerrates")
public class WrkrRatingContrl {
	
	@Autowired
	private WrkrRatingServ ratserv;
	
	@PostMapping("/worker")
	public ResponseEntity<?> rateWorker(@Valid @RequestBody WorkerRatingDto dto,Authentication authentication){
		System.out.println("Controller reached");
		return ResponseEntity.ok(ratserv.rateWorker(dto,authentication.getName()));
	}

}

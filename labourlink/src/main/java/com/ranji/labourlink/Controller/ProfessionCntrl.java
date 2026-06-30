package com.ranji.labourlink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranji.labourlink.Service.ProfessionServ;

@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/api/profession")
public class ProfessionCntrl {
	@Autowired
	public ProfessionServ profserv;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllProf(){
		return ResponseEntity.ok(profserv.getAllProf());
	}
}

package com.ranji.labourlink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranji.labourlink.Service.WrkWageServ;

@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/api/wages")
public class WrkWageCntrl {
	
	@Autowired
	private WrkWageServ wageserv;
	
	@GetMapping("/all")
	public ResponseEntity<?> allWage(){
		return ResponseEntity.ok(wageserv.getAllWages());
	}
	@GetMapping("/{profession}")
	public ResponseEntity<?> profwage(@PathVariable String profession){
		return wageserv.profwage(profession);
	}
}

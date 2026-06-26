package com.ranji.labourlink.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.dto.LoginDto;
import com.ranji.labourlink.dto.registerDto;

@Service
public class UserLoginServ {
	@Autowired
	public UserLoginRepo logrep; 
	
	public ResponseEntity<String> login(LoginDto log) {
		User cust = new User();
		 cust = logrep.findByphno(log.getPhoneNumber());
		 if(cust==null) return new ResponseEntity<>("No User Found",HttpStatus.BAD_REQUEST);
		 else {
			 if(cust.getPassword().equals(log.getPassword())) {
				 return new ResponseEntity<>("Succussfull Login",HttpStatus.ACCEPTED);
			 }
			 else {
				 return new ResponseEntity<>("Incorrect Password",HttpStatus.BAD_REQUEST);
			 }
		 }
	}

	public ResponseEntity<String> register(registerDto cust) {
		User customer = new User();
		
		customer = logrep.findByphno(cust.getPhoneNumber());
		if (customer!=null) {
	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body("Phone number already registered");
	    }
		customer.setName(cust.getName());
		customer.setPassword(cust.getPassword());
		customer.setPhoneNumber(cust.getPhoneNumber());
		customer.setCreatedAt(LocalDateTime.now());
		logrep.save(customer);
		return new ResponseEntity<>("Account Created",HttpStatus.ACCEPTED);
		}
}

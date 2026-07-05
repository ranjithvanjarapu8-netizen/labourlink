package com.ranji.labourlink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Service.UserLoginServ;
import com.ranji.labourlink.dto.LoginDto;
import com.ranji.labourlink.dto.LoginResponse;
import com.ranji.labourlink.dto.registerDto;


@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/api/auth")
public class UserLogin {
	
	@Autowired 
	public UserLoginServ logserv;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> Login(@RequestBody LoginDto log){
		return logserv.login(log);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody registerDto cust){
		return logserv.register(cust);
	}
}

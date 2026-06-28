package com.ranji.labourlink.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Service.JobServ;
import com.ranji.labourlink.dto.JobListDto;
import com.ranji.labourlink.dto.JobPostDto;

@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/api/job")
public class JobCntrl {
	@Autowired
	private JobServ jobserv;
	
	@Autowired
	private UserLoginRepo userrep;
	
	@PostMapping("/create")
    public ResponseEntity<String> createJob(
            @RequestBody JobPostDto jobdto,
            Authentication authentication) {

        String phone = authentication.getName();

        User user = userrep.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String message = jobserv.createJob(user, jobdto);

        return ResponseEntity.ok(message);
    }
	@GetMapping("/all")
	public ResponseEntity<List<JobListDto>> jobs(){
		return jobserv.jobs();
	}

}

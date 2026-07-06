package com.ranji.labourlink.Controller;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Repository.WorkerRepo;
import com.ranji.labourlink.Service.WorkerServ;
import com.ranji.labourlink.dto.OwnrWrkrProfileDto;
import com.ranji.labourlink.dto.UserInfoDto;
import com.ranji.labourlink.dto.WorkerCardDto;
import com.ranji.labourlink.dto.WorkerRegisterDto;
import com.ranji.labourlink.dto.WrkrProfileDto;

@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/api/worker")
public class WorkerContrl {
	@Autowired
	private WorkerServ wrkrser;
	
	@Autowired
	private WorkerRepo wrkrrep;
	
	@Autowired
	private UserLoginRepo userrep;
	
	@Autowired
	private WorkerServ wrkrserv;
	

	@PostMapping(value = "/register", consumes = "multipart/form-data")
	public ResponseEntity<String> registerWorker(

	        @RequestPart("worker") WorkerRegisterDto wrkrdto,

	        @RequestPart("photo") MultipartFile photo,

	        Authentication authentication) {

	    if (authentication == null) {
	        return ResponseEntity.badRequest().body("Authentication is null");
	    }

	    String phone = authentication.getName();

	    User user = userrep.findByPhoneNumber(phone)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    String message = wrkrser.registerWorker(user, wrkrdto, photo);

	    return ResponseEntity.ok(message);
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> wrkdetls(Authentication authentication){
		String phone = authentication.getName();
		User user = userrep.findByPhoneNumber(phone)
	            .orElseThrow(() -> new RuntimeException("User not found"));
		Optional<Worker> wrkr = wrkrrep.findByUser(user);
		if (wrkr.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            .body("Worker Not Found");
		}
		return ResponseEntity.ok(wrkrserv.getWorkerProfile(user));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<WorkerCardDto>> allwrkrs(){
		return wrkrser.allwrkrs();
	}
	@GetMapping("/nearby")
	public ResponseEntity<List<WorkerCardDto>> getNearbyWorkers(
	        @RequestParam Double lat,
	        @RequestParam Double lon,@RequestParam(required = false) String profession,@RequestParam String date){

	    return ResponseEntity.ok(wrkrserv.getNearbyWorkers(lat, lon,profession,date));
	}
	@GetMapping("/profile/{id}")
	public ResponseEntity<OwnrWrkrProfileDto> getIdWrkr(@PathVariable Long id){
		return wrkrserv.getIdWrkr(id);
	}
	@GetMapping("/userinfo")
	public ResponseEntity<UserInfoDto> getUserInfo(Authentication authentication) {

	    String phone = authentication.getName();

	    User user = userrep.findByPhoneNumber(phone)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    UserInfoDto dto = new UserInfoDto();

	    dto.setName(user.getName());
	    dto.setPhoneNumber(user.getPhoneNumber());

	    return ResponseEntity.ok(dto);
	}
}

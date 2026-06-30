package com.ranji.labourlink.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranji.labourlink.Service.RequestServ;
import com.ranji.labourlink.dto.AcceptedRequestDto;
import com.ranji.labourlink.dto.IncomingRequestDto;
import com.ranji.labourlink.dto.RequestDetailsDto;
import com.ranji.labourlink.dto.SendRequestDto;

@CrossOrigin(origins = {"http://127.0.0.1:5501",
        "http://127.0.0.1:5500",
        "http://localhost:5501",
        "http://localhost:5500"})
@RestController
@RequestMapping("/api/work")
public class RequestCntrl {
	@Autowired
	private RequestServ requestService;
	
	@PostMapping("/send")
    public ResponseEntity<String> sendRequest(
            @RequestBody SendRequestDto dto,
            Authentication authentication) {

        return ResponseEntity.ok(
                requestService.sendRequest(authentication.getName(), dto));
    }
	
	@GetMapping("/incoming")
    public ResponseEntity<List<IncomingRequestDto>> getIncomingRequests(
            Authentication authentication) {

        return ResponseEntity.ok(
                requestService.getIncomingRequests(authentication.getName()));
    }
	
    @GetMapping("/my")
    public ResponseEntity<?> getMyRequests(
            Authentication authentication) {

        return ResponseEntity.ok(
                requestService.getMyRequests(authentication.getName()));
    }

    // View complete request details
    @GetMapping("/details/{requestId}")
    public ResponseEntity<RequestDetailsDto> getRequestDetails(
            @PathVariable Long requestId,
            Authentication authentication) {

        return ResponseEntity.ok(
                requestService.getRequestDetails(authentication.getName(), requestId));
    }

    // Worker accepts a request
    @PutMapping("/accept/{requestId}")
    public ResponseEntity<String> acceptRequest(
            @PathVariable Long requestId,
            Authentication authentication) {

        return ResponseEntity.ok(
                requestService.acceptRequest(authentication.getName(), requestId));
    }

    // Worker rejects a request
    @PutMapping("/reject/{requestId}")
    public ResponseEntity<String> rejectRequest(
            @PathVariable Long requestId,
            Authentication authentication) {

        return ResponseEntity.ok(
                requestService.rejectRequest(authentication.getName(), requestId));
    }
    
    @GetMapping("/accepted")
    public ResponseEntity<List<AcceptedRequestDto>> getallaccepted(Authentication authentication){
    	return ResponseEntity.ok(requestService.getallaccepted(authentication.getName()));
    }
    
    @GetMapping("/completed")
    public ResponseEntity<List<AcceptedRequestDto>> getallcompleted(Authentication authentication){
    	return ResponseEntity.ok(requestService.getallcompleted(authentication.getName()));
    }
}

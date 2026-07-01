package com.ranji.labourlink.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Repository.WrkRequestRepo;
import com.ranji.labourlink.dto.OwnerProfileDto;
import com.ranji.labourlink.dto.OwnerRequestDto;
import com.ranji.labourlink.dto.OwnerRequestsResponse;

@Service
public class OwnerService {

	@Autowired
	private WrkRequestRepo requestRepo;
	
	@Autowired
	private UserLoginRepo userRepo;
	
	public OwnerRequestsResponse getOwnerRequests(User owner) {

	    List<OwnerRequestDto> requests =
	            requestRepo.findOwnerRequests(owner.getId());

	    LocalDateTime now = LocalDateTime.now();

	    List<OwnerRequestDto> upcoming = new ArrayList<>();
	    List<OwnerRequestDto> past = new ArrayList<>();

	    for (OwnerRequestDto request : requests) {

	        LocalDateTime workEnds = LocalDateTime.of(
	                request.getWorkDate(),
	                request.getEndTime()
	        );

	        if (workEnds.isAfter(now)) {
	            upcoming.add(request);
	        } else {
	            past.add(request);
	        }
	    }

	    upcoming.sort(
	            Comparator.comparing(OwnerRequestDto::getWorkDate)
	                      .thenComparing(OwnerRequestDto::getStartTime)
	    );

	    past.sort(
	            Comparator.comparing(OwnerRequestDto::getWorkDate)
	                      .thenComparing(OwnerRequestDto::getStartTime)
	                      .reversed()
	    );

	    return new OwnerRequestsResponse(requests.size(),upcoming.size(),past.size(),upcoming, past);
	}
	

	public OwnerProfileDto getOwnerProfile(String phone) {

	    User owner = userRepo.findByPhoneNumber(phone)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    OwnerRequestsResponse response = getOwnerRequests(owner);

	    OwnerProfileDto dto = new OwnerProfileDto();

	    dto.setName(owner.getName());
	    dto.setPhoneNumber(owner.getPhoneNumber());
	    dto.setJoinedDate(owner.getCreatedAt());

	    dto.setTotalRequests(response.getTotalRequests());
	    dto.setUpcomingRequests(response.getUpcomingCount());
	    dto.setPastRequests(response.getPastCount());

	    return dto;
	}

}

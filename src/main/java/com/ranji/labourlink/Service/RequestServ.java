package com.ranji.labourlink.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ranji.labourlink.Model.Profession;
import com.ranji.labourlink.Model.RequestStatusEnum;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.WorkRequest;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Model.WorkerLocation;
import com.ranji.labourlink.Model.WorkerRating;
import com.ranji.labourlink.Repository.ProfessionRepo;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Repository.WorkerLocationRepo;
import com.ranji.labourlink.Repository.WorkerRatingRepo;
import com.ranji.labourlink.Repository.WorkerRepo;
import com.ranji.labourlink.Repository.WrkRequestRepo;
import com.ranji.labourlink.dto.AcceptedRequestDto;
import com.ranji.labourlink.dto.IncomingRequestDto;
import com.ranji.labourlink.dto.OwnerCompletedRequestDto;
import com.ranji.labourlink.dto.RequestDetailsDto;
import com.ranji.labourlink.dto.SendRequestDto;

import jakarta.transaction.Transactional;

@Service
public class RequestServ {
	
	@Autowired
	private UserLoginRepo userRepo;
	
	@Autowired
	private WorkerRepo workerRepo;
	
	@Autowired
	private ProfessionRepo professionRepo;
	
	@Autowired
	private WrkRequestRepo requestRepo;
	
	@Autowired
	private WorkerRatingRepo workerRatingRepo;

	@Autowired
	private WorkerLocationRepo workerLocationRepo;
	
	public String sendRequest(String phoneNumber, SendRequestDto dto) {

	    User owner = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("Owner not found"));

	    Worker worker = workerRepo.findById(dto.getWorkerId())
	            .orElseThrow(() -> new RuntimeException("Worker not found"));

	    Profession profession = professionRepo.findById(dto.getProfessionId())
	            .orElseThrow(() -> new RuntimeException("Profession not found"));

	    WorkRequest request = new WorkRequest();

	    request.setOwner(owner);
	    request.setWorker(worker);
	    request.setProfession(profession);

	    request.setTitle(dto.getTitle());
	    request.setDescription(dto.getDescription());

	    request.setAddress(dto.getAddress());
	    request.setLatitude(dto.getLatitude());
	    request.setLongitude(dto.getLongitude());

	    request.setWorkDate(dto.getWorkDate());
	    request.setStartTime(dto.getStartTime());
	    request.setEndTime(dto.getEndTime());

	    request.setStatus(RequestStatusEnum.PENDING);

	    requestRepo.save(request);

	    return "Request sent successfully.";
	}

	public List<IncomingRequestDto> getIncomingRequests(String phoneNumber) {

	    User user = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Worker worker = workerRepo.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Worker profile not found"));

	    List<WorkRequest> requests = requestRepo
	            .findByWorkerAndStatusOrderByCreatedAtDesc(worker, RequestStatusEnum.PENDING);

	    List<IncomingRequestDto> response = new ArrayList<>();

	    for (WorkRequest request : requests) {	

	        double distance = calculateDistance(
	                worker.getLatitude(),
	                worker.getLongitude(),
	                request.getLatitude(),
	                request.getLongitude());

	        IncomingRequestDto dto = new IncomingRequestDto(
	                request.getId(),
	                request.getOwner().getName(),
	                request.getProfession().getName(),
	                request.getTitle(),
	                request.getDescription(),
	                distance,
	                request.getWorkDate(),
	                request.getStartTime(),
	                request.getEndTime(),
	                request.getStatus());

	        response.add(dto);
	    }

	    return response;
	}
	public static double calculateDistance(
	        double lat1,
	        double lon1,
	        double lat2,
	        double lon2) {

	    final int R = 6371; // Earth's radius in km

	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);

	    double a =
	            Math.sin(dLat / 2) * Math.sin(dLat / 2)
	            + Math.cos(Math.toRadians(lat1))
	            * Math.cos(Math.toRadians(lat2))
	            * Math.sin(dLon / 2)
	            * Math.sin(dLon / 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return R * c;
	}

	public List<RequestDetailsDto> getMyRequests(String phoneNumber) {

	    User owner = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("Owner not found"));

	    List<WorkRequest> requests = requestRepo.findByOwnerOrderByCreatedAtDesc(owner);

	    List<RequestDetailsDto> response = new ArrayList<>();

	    for (WorkRequest request : requests) {

	        Worker worker = request.getWorker();

	        double distance = calculateDistance(
	                worker.getLatitude(),
	                worker.getLongitude(),
	                request.getLatitude(),
	                request.getLongitude());

	        RequestDetailsDto dto = new RequestDetailsDto();

	        dto.setRequestId(request.getId());

	        dto.setOwnerName(owner.getName());

	        dto.setWorkerName(worker.getUser().getName());

	        dto.setProfession(request.getProfession().getName());

	        dto.setTitle(request.getTitle());
	        dto.setDescription(request.getDescription());

	        dto.setDistance(distance);

	        dto.setWorkDate(request.getWorkDate());
	        dto.setStartTime(request.getStartTime());
	        dto.setEndTime(request.getEndTime());

	        dto.setStatus(request.getStatus());

	        // Share contact details only after acceptance
	        if (request.getStatus() == RequestStatusEnum.ACCEPTED
	                || request.getStatus() == RequestStatusEnum.COMPLETED) {

	            dto.setOwnerPhone(owner.getPhoneNumber());
	            dto.setWorkerPhone(worker.getUser().getPhoneNumber());

	            dto.setAddress(request.getAddress());
	            dto.setLatitude(request.getLatitude());
	            dto.setLongitude(request.getLongitude());
	        }

	        response.add(dto);
	    }

	    return response;
	}

	public RequestDetailsDto getRequestDetails(String phoneNumber, Long requestId) {

	    User user = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	
	    WorkRequest request = requestRepo.findByIdWithDetails(requestId)
	            .orElseThrow(() -> new RuntimeException("Request not found"));
	
	    // Allow only the owner or the assigned worker
	    boolean isOwner = request.getOwner().getId().equals(user.getId());
	
	    boolean isWorker = request.getWorker().getUser().getId().equals(user.getId());
	
	    if (!isOwner && !isWorker) {
	        throw new RuntimeException("You are not authorized to view this request.");
	    }
	
	    double distance = calculateDistance(
	            request.getWorker().getLatitude(),
	            request.getWorker().getLongitude(),
	            request.getLatitude(),
	            request.getLongitude());
	
	    RequestDetailsDto dto = new RequestDetailsDto();
	
	    dto.setRequestId(request.getId());
	
	    dto.setOwnerName(request.getOwner().getName());
	    dto.setWorkerName(request.getWorker().getUser().getName());
	
	    dto.setProfession(request.getProfession().getName());
	
	    dto.setTitle(request.getTitle());
	    dto.setDescription(request.getDescription());
	
	    dto.setDistance(distance);
	
	    dto.setWorkDate(request.getWorkDate());
	    dto.setStartTime(request.getStartTime());
	    dto.setEndTime(request.getEndTime());
	
	    dto.setStatus(request.getStatus());
	
	    // Share sensitive information only after acceptance
	    if (request.getStatus() == RequestStatusEnum.ACCEPTED
	            || request.getStatus() == RequestStatusEnum.COMPLETED) {
	
	        dto.setOwnerPhone(request.getOwner().getPhoneNumber());
	        dto.setWorkerPhone(request.getWorker().getUser().getPhoneNumber());
	
	        dto.setAddress(request.getAddress());
	        dto.setLatitude(request.getLatitude());
	        dto.setLongitude(request.getLongitude());
	    }
	
	    return dto;
	}

	public String acceptRequest(String phoneNumber, Long requestId) {

	    User user = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	
	    Worker worker = workerRepo.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Worker profile not found"));
	
	    WorkRequest request = requestRepo.findByIdAndWorker(requestId, worker)
	            .orElseThrow(() -> new RuntimeException("Request not found"));
	
	    if (request.getStatus() != RequestStatusEnum.PENDING) {
	        throw new ResponseStatusException(
	            HttpStatus.BAD_REQUEST,
	            "This request has already been accepted."
	        );
	    }
	
	    request.setStatus(RequestStatusEnum.ACCEPTED);
	
	    requestRepo.save(request);
	    workerRepo.save(worker);
	
	    return "Request accepted successfully.";
	}
	public String rejectRequest(String phoneNumber, Long requestId) {

	    User user = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Worker worker = workerRepo.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Worker profile not found"));

	    WorkRequest request = requestRepo.findByIdAndWorker(requestId, worker)
	            .orElseThrow(() -> new RuntimeException("Request not found"));

	    if (request.getStatus() != RequestStatusEnum.PENDING) {
	        throw new ResponseStatusException(
	            HttpStatus.BAD_REQUEST,
	            "This request is no longer pending."
	        );
	    }

	    request.setStatus(RequestStatusEnum.REJECTED);

	    requestRepo.save(request);

	    return "Request rejected successfully.";
	}

	public List<AcceptedRequestDto> getallaccepted(String phoneNumber) {

	    User user = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Worker worker = workerRepo.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Worker profile not found"));

	    List<WorkRequest> requests = requestRepo
	            .findByWorkerAndStatusOrderByCreatedAtDesc(
	                    worker,
	                    RequestStatusEnum.ACCEPTED);

	    List<AcceptedRequestDto> response = new ArrayList<>();

	    for (WorkRequest request : requests) {

	        double distance = calculateDistance(
	                worker.getLatitude(),
	                worker.getLongitude(),
	                request.getLatitude(),
	                request.getLongitude());

	        AcceptedRequestDto dto = new AcceptedRequestDto();

	        dto.setRequestId(request.getId());
	        dto.setOwnerName(request.getOwner().getName());
	        dto.setOwnerPhone(request.getOwner().getPhoneNumber());

	        dto.setProfession(request.getProfession().getName());

	        dto.setTitle(request.getTitle());
	        dto.setDescription(request.getDescription());
	        dto.setAddress(request.getAddress());

	        dto.setDistance(distance);

	        dto.setWorkDate(request.getWorkDate());
	        dto.setStartTime(request.getStartTime());
	        dto.setEndTime(request.getEndTime());

	        dto.setLatitude(request.getLatitude());
	        dto.setLongitude(request.getLongitude());

	        dto.setStatus(request.getStatus());

	        response.add(dto);
	    }

	    return response;
	}

	public List<AcceptedRequestDto> getallcompleted(String phoneNumber) {

	    User user = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Worker worker = workerRepo.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Worker profile not found"));

	    List<WorkRequest> requests = requestRepo
	            .findByWorkerAndStatusOrderByCreatedAtDesc(
	                    worker,
	                    RequestStatusEnum.COMPLETED);

	    List<AcceptedRequestDto> response = new ArrayList<>();

	    worker.setTotalJobs(requests.size());

	    for (WorkRequest request : requests) {

	        double distance = calculateDistance(
	                worker.getLatitude(),
	                worker.getLongitude(),
	                request.getLatitude(),
	                request.getLongitude());

	        AcceptedRequestDto dto = new AcceptedRequestDto();

	        dto.setRequestId(request.getId());
	        dto.setOwnerName(request.getOwner().getName());
	        dto.setOwnerPhone(request.getOwner().getPhoneNumber());

	        dto.setProfession(request.getProfession().getName());

	        dto.setTitle(request.getTitle());
	        dto.setDescription(request.getDescription());
	        dto.setAddress(request.getAddress());

	        dto.setDistance(distance);

	        dto.setWorkDate(request.getWorkDate());
	        dto.setStartTime(request.getStartTime());
	        dto.setEndTime(request.getEndTime());

	        dto.setLatitude(request.getLatitude());
	        dto.setLongitude(request.getLongitude());

	        dto.setStatus(request.getStatus());

	        // ================= Rating =================

	        Optional<WorkerRating> workerRating =
	                workerRatingRepo.findByRequest(request);

	        if (workerRating.isPresent()) {

	            dto.setRating(workerRating.get().getStars());

	        } else {

	            dto.setRating(null);

	        }

	        // ==========================================

	        response.add(dto);
	    }

	    return response;
	}
	
	@Scheduled(initialDelay = 1000, fixedRate = 60000)
	@Transactional
	public void updateRequestStatuses() {

	    List<WorkRequest> requests = requestRepo.findAll();

	    LocalDateTime now = LocalDateTime.now();

	    for (WorkRequest request : requests) {

	        LocalDateTime workStarts = LocalDateTime.of(
	                request.getWorkDate(),
	                request.getStartTime());

	        LocalDateTime workEnds = LocalDateTime.of(
	                request.getWorkDate(),
	                request.getEndTime());

	        // Reject pending requests after work end time
	        if (request.getStatus() == RequestStatusEnum.PENDING
	                && now.isAfter(workEnds)) {

	            request.setStatus(RequestStatusEnum.REJECTED);
	        }

	        // Handle accepted requests
	        else if (request.getStatus() == RequestStatusEnum.ACCEPTED) {

	            Worker worker = request.getWorker();

	            WorkerLocation location = workerLocationRepo
	                    .findByWorker(worker)
	                    .orElseGet(() -> {

	                        WorkerLocation wl = new WorkerLocation();

	                        wl.setWorker(worker);
	                        wl.setLatitude(worker.getLatitude());
	                        wl.setLongitude(worker.getLongitude());
	                        wl.setLastUpdated(LocalDateTime.now());
	                        wl.setTrackingEnabled(false);

	                        return workerLocationRepo.save(wl);
	                    });

	            LocalDateTime trackingStarts = workStarts.minusHours(1);

	            // Enable tracking 1 hour before work starts
	            if (now.isAfter(trackingStarts)
	                    && now.isBefore(workEnds)
	                    && !location.getTrackingEnabled()) {

	                location.setTrackingEnabled(true);
	                workerLocationRepo.save(location);
	            }

	            // Work completed
	            if (now.isAfter(workEnds)) {

	                request.setStatus(RequestStatusEnum.COMPLETED);

	                location.setTrackingEnabled(false);
	                workerLocationRepo.save(location);
	            }
	        }
	    }

	    // Save all request status changes
	    requestRepo.saveAll(requests);

	    // Update worker completed job count
	    List<Worker> workers = workerRepo.findAll();

	    for (Worker worker : workers) {

	        long completedJobs = requestRepo.countByWorkerAndStatus(
	                worker,
	                RequestStatusEnum.COMPLETED);

	        worker.setTotalJobs((int) completedJobs);

	        boolean hasActiveJob = requestRepo.existsByWorkerAndStatus(
	                worker,
	                RequestStatusEnum.ACCEPTED);

	        // Optional:
	        // worker.setAvailable(!hasActiveJob);
	    }

	    workerRepo.saveAll(workers);
	}

	public List<OwnerCompletedRequestDto> getOwnerCompletedRequests(String phoneNumber) {

	    User owner = userRepo.findByPhoneNumber(phoneNumber)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    List<WorkRequest> requests =
	            requestRepo.findCompletedRequestsWithWorker(
	                    owner,
	                    RequestStatusEnum.COMPLETED);

	    List<OwnerCompletedRequestDto> response = new ArrayList<>();

	    for (WorkRequest request : requests) {

	        OwnerCompletedRequestDto dto = new OwnerCompletedRequestDto();

	        dto.setRequestId(request.getId());
	        dto.setTitle(request.getTitle());

	        dto.setWorkerName(
	                request.getWorker().getUser().getName()
	        );

	        dto.setProfession(
	                request.getProfession().getName()
	        );

	        dto.setDescription(request.getDescription());
	        dto.setAddress(request.getAddress());

	        dto.setWorkDate(request.getWorkDate());
	        dto.setStartTime(request.getStartTime());
	        dto.setEndTime(request.getEndTime());

	        dto.setStatus(request.getStatus().name());

	        Optional<WorkerRating> rating =
	                workerRatingRepo.findByRequest(request);

	        if (rating.isPresent()) {

	            dto.setRating(
	                    rating.get().getStars()
	            );

	        } else {

	            dto.setRating(null);

	        }

	        response.add(dto);

	    }

	    return response;
	}

}

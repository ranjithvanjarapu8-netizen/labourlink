package com.ranji.labourlink.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.RequestStatusEnum;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.WorkRequest;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Model.WorkerLocation;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Repository.WorkerLocationRepo;
import com.ranji.labourlink.Repository.WorkerRepo;
import com.ranji.labourlink.Repository.WrkRequestRepo;
import com.ranji.labourlink.dto.LiveLocationDto;
import com.ranji.labourlink.dto.LocationDto;
import com.ranji.labourlink.dto.TrackingStatusDto;
import com.ranji.labourlink.dto.WorkerLocationResponseDto;

@Service
public class WorkerLocationService {

    @Autowired
    private WorkerLocationRepo workerLocationRepo;
    
    @Autowired
    private UserLoginRepo userRepo;

    @Autowired
    private WorkerRepo workerRepo;
    
    @Autowired
    private WrkRequestRepo workRequestRepo;
    
    public String updateLocation(String phoneNumber, LocationDto dto) {

        User user = userRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Worker worker = workerRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Worker profile not found"));

        Optional<WorkerLocation> optionalLocation =
                workerLocationRepo.findByWorker(worker);

        WorkerLocation location;

        if (optionalLocation.isPresent()) {
            location = optionalLocation.get();
        } else {
            location = new WorkerLocation();
            location.setWorker(worker);
            location.setTrackingEnabled(false);
        }

        if (!Boolean.TRUE.equals(location.getTrackingEnabled())) {
            return "Tracking is disabled.";
        }

        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setLastUpdated(LocalDateTime.now());

        workerLocationRepo.save(location);

        return "Location updated successfully.";
    }
    
    
    public WorkerLocationResponseDto getWorkerLocation(Long workerId) {

        Worker worker = workerRepo.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        WorkerLocation location = workerLocationRepo.findByWorker(worker)
                .orElseThrow(() -> new RuntimeException("Worker location not found"));

        WorkerLocationResponseDto dto = new WorkerLocationResponseDto();

        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        dto.setLastUpdated(location.getLastUpdated());

        return dto;
    }
    
    
    public LiveLocationDto getLiveLocation(Long requestId) {
    	

        WorkRequest request = workRequestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Work request not found"));
        
        if (request.getStatus() != RequestStatusEnum.ACCEPTED) {
            throw new RuntimeException("Live tracking is not available for this request.");
        }

        WorkerLocation location = workerLocationRepo.findByWorker(request.getWorker())
                .orElseThrow(() -> new RuntimeException("Worker location not found"));

        LiveLocationDto dto = new LiveLocationDto();
        System.out.println("Request ID: " + requestId);
    	System.out.println("Worker ID: " + request.getWorker().getId());
        dto.setRequestId(request.getId());
        dto.setWorkerId(request.getWorker().getId());

        dto.setWorkerLatitude(location.getLatitude());
        dto.setWorkerLongitude(location.getLongitude());

        dto.setWorkLatitude(request.getLatitude());
        dto.setWorkLongitude(request.getLongitude());

        dto.setStatus(request.getStatus().name());

        dto.setLastUpdated(location.getLastUpdated());

        return dto;
    }
    public TrackingStatusDto getTrackingStatus(String phoneNumber) {

        User user = userRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Worker worker = workerRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        Optional<WorkerLocation> optional =
                workerLocationRepo.findByWorker(worker);

        if (optional.isEmpty()) {
            return new TrackingStatusDto(false);
        }

        return new TrackingStatusDto(
                optional.get().getTrackingEnabled()
        );
    }
}
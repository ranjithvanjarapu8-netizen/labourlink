package com.ranji.labourlink.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.WorkerLocation;
import com.ranji.labourlink.Service.WorkerLocationService;
import com.ranji.labourlink.dto.LiveLocationDto;
import com.ranji.labourlink.dto.LocationDto;
import com.ranji.labourlink.dto.TrackingStatusDto;
import com.ranji.labourlink.dto.WorkerLocationResponseDto;

@RestController
@RequestMapping("/worker")
public class WorkerLocationController {

    @Autowired
    private WorkerLocationService workerLocationService;
    @PostMapping("/location")
    public ResponseEntity<String> updateLocation(
            Authentication authentication,
            @RequestBody LocationDto dto) {

        String phoneNumber = authentication.getName();

        String message = workerLocationService.updateLocation(phoneNumber, dto);

        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/location/{workerId}")
    public ResponseEntity<WorkerLocationResponseDto> getWorkerLocation(
            @PathVariable Long workerId) {

        return ResponseEntity.ok(
                workerLocationService.getWorkerLocation(workerId)
        );
    }
    
    @GetMapping("/{requestId}/live-location")
    public ResponseEntity<LiveLocationDto> getLiveLocation(
            @PathVariable Long requestId) {

        return ResponseEntity.ok(
                workerLocationService.getLiveLocation(requestId)
        );
    }
    @GetMapping("/location/status")
    public ResponseEntity<TrackingStatusDto> getTrackingStatus(
            Authentication authentication) {

        String phoneNumber = authentication.getName();

        return ResponseEntity.ok(
                workerLocationService.getTrackingStatus(phoneNumber)
        );
    }
    
}
package com.ranji.labourlink.Service;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ranji.labourlink.Model.RequestStatusEnum;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.WorkRequest;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Model.WorkerRating;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Repository.WorkerRatingRepo;
import com.ranji.labourlink.Repository.WorkerRepo;
import com.ranji.labourlink.Repository.WrkRequestRepo;
import com.ranji.labourlink.dto.WorkerRatingDto;

import jakarta.validation.Valid;

@Service
public class WrkrRatingServ {
	@Autowired
	private WorkerRatingRepo ratrep;
	
	@Autowired
	private UserLoginRepo userRepo;
	
	@Autowired
	private WrkRequestRepo requestRepo;
	
	@Autowired
	private WorkerRepo workerRepo;
	
	@Transactional
	public @Nullable Object rateWorker(@Valid WorkerRatingDto dto,String phone) {
		
		 System.out.println("Service reached");
		User owner = userRepo.findByPhoneNumber(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find request
        WorkRequest request = requestRepo.findById(dto.getRequestId())
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Security check
        if (!request.getOwner().getId().equals(owner.getId())) {
            throw new RuntimeException("You are not allowed to rate this worker.");
        }

        // Work must be completed
        if (request.getStatus() != RequestStatusEnum.COMPLETED) {
            throw new RuntimeException("Work is not completed yet.");
        }

        // Already rated
        if (request.isWorkerRated()) {
            throw new RuntimeException("Worker already rated.");
        }

        Worker worker = request.getWorker();

        // Save rating
        WorkerRating rating = new WorkerRating();
        rating.setRequest(request);
        rating.setWorker(worker);
        rating.setStars(dto.getStars());

        ratrep.save(rating);

        // Update worker average rating
        int totalRatings = worker.getTotalRatings();
        double currentRating = worker.getRating();

        double newAverage =
                ((currentRating * totalRatings) + dto.getStars())
                        / (totalRatings + 1);
        newAverage = Math.round(newAverage * 10.0) / 10.0;

        worker.setRating(newAverage);
        worker.setTotalRatings(totalRatings + 1);

        workerRepo.save(worker);

        // Mark request as rated
        request.setWorkerRated(true);
        requestRepo.save(request);

        return "Worker rated successfully.";
	}
}

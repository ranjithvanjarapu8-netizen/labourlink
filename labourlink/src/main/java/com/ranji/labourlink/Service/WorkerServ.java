package com.ranji.labourlink.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Repository.WorkerRepo;
import com.ranji.labourlink.dto.WorkerCardDto;
import com.ranji.labourlink.dto.WorkerRegisterDto;
import com.ranji.labourlink.dto.wrkrProfileDto;

@Service
public class WorkerServ {
	@Autowired
	private WorkerRepo wrkrrep;
	
	@Autowired
	private UserLoginRepo userrep;
	
	public boolean hasWorkerProfile(User user) {
	    return wrkrrep.existsByUser(user);
	}
	
	public String registerWorker(User user, WorkerRegisterDto dto, MultipartFile photo) {

	    if(wrkrrep.existsByUser(user)) {
	        return "Worker profile already exists.";
	    }
	    if (photo == null || photo.isEmpty()) {
	        return "Please upload a profile photo.";
	    }
	    String type = photo.getContentType();

	    if(type == null || !type.startsWith("image/")){
	        return "Only image files are allowed.";
	    }
	    String uploadDir = "uploads/workers/";

	    String fileName =
	            System.currentTimeMillis() + "_" + photo.getOriginalFilename();

	    Path path = Paths.get(uploadDir);

	    if (!Files.exists(path)) {
	        try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Unable to save profile photo.", e);
			}
	    }

	    try {
	        photo.transferTo(path.resolve(fileName));
	    } catch (IOException | IllegalStateException e) {
	        throw new RuntimeException("Unable to save profile photo.", e);
	    }
	    Worker worker = new Worker();

	    worker.setUser(user);
	    worker.setProfession(dto.getProfession());
	    worker.setExperience(dto.getExperience());
	    worker.setLatitude(dto.getLatitude());
	    worker.setLongitude(dto.getLongitude());
	    worker.setAadhaarNumber(dto.getAadhaarNumber());
	    worker.setAvailable(true);
	    worker.setProfilePhoto(fileName);
	    wrkrrep.save(worker);

	    return "Worker profile created successfully.";
	}
	public wrkrProfileDto getWorkerProfile(User user) {
		
	    Worker worker = wrkrrep.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Worker profile not found"));

	    wrkrProfileDto dto = new  wrkrProfileDto();

	    dto.setName(user.getName());
	    dto.setPhoneNumber(user.getPhoneNumber());

	    dto.setProfession(worker.getProfession());
	    dto.setExperience(worker.getExperience());
	    dto.setRating(worker.getRating());
	    dto.setTotalJobs(worker.getTotalJobs());
	    dto.setAvailable(worker.getAvailable());
	    dto.setLatitude(worker.getLatitude());
	    dto.setLongitude(worker.getLongitude());
	    dto.setProfilePhoto(worker.getProfilePhoto());

	    return dto;
	}

	public ResponseEntity<List<WorkerCardDto>> allwrkrs() {
		List<WorkerCardDto> ans = wrkrrep.findAllDto();
		return ResponseEntity.ok(ans);
	}

	public List<WorkerCardDto> getNearbyWorkers(double lat,double lon){

	    List<Worker> workers = wrkrrep.findAllWithUser();

	    List<WorkerCardDto> ans = new ArrayList<>();

	    for(Worker worker : workers){

	        if(!worker.getAvailable())
	            continue;

	        double distance = calculateDistance(
	                lat,
	                lon,
	                worker.getLatitude(),
	                worker.getLongitude());

	        if(distance <= 20){

	            WorkerCardDto dto = new WorkerCardDto();
	            dto.setWorkerId(worker.getId());
	            dto.setName(worker.getUser().getName());
	            dto.setProfession(worker.getProfession());
	            dto.setExperience(worker.getExperience());
	            dto.setRating(worker.getRating());
	            dto.setTotalJobs(worker.getTotalJobs());
	            dto.setProfilePhoto(worker.getProfilePhoto());

	            dto.setDistance(
	                    Math.round(distance * 10.0) / 10.0
	            );

	            ans.add(dto);

	        }

	    }
	    ans.sort(Comparator.comparing(WorkerCardDto::getDistance));

	    return ans;
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
}

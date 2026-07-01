package com.ranji.labourlink.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ranji.labourlink.Model.Profession;
import com.ranji.labourlink.Model.RequestStatusEnum;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Repository.ProfessionRepo;
import com.ranji.labourlink.Repository.UserLoginRepo;
import com.ranji.labourlink.Repository.WorkerRepo;
import com.ranji.labourlink.Repository.WrkRequestRepo;
import com.ranji.labourlink.dto.OwnrWrkrProfileDto;
import com.ranji.labourlink.dto.WorkerCardDto;
import com.ranji.labourlink.dto.WorkerRegisterDto;
import com.ranji.labourlink.dto.WrkrProfileDto;
import com.ranji.labourlink.dto.WrkrProfileDto;

@Service
public class WorkerServ {
	@Autowired
	private WorkerRepo wrkrrep;
	
	@Autowired
	private UserLoginRepo userrep;
	
	@Autowired
	private ProfessionRepo profrep;
	
	@Autowired
	private WrkRequestRepo requestRepo;
	
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

	    worker.setExperience(dto.getExperience());

	    worker.setCity(dto.getCity());
	    worker.setDistrict(dto.getDistrict());
	    worker.setState(dto.getState());

	    List<Profession> professions =profrep.findAllById(dto.getProfessionIds());

	    worker.setProfessions(professions);

	    worker.setLatitude(dto.getLatitude());
	    worker.setLongitude(dto.getLongitude());

	    worker.setAadhaarNumber(dto.getAadhaarNumber());
	    worker.setLanguages(dto.getLanguages());
	    worker.setDescription(dto.getDescription());

	    worker.setAvailable(true);

	    worker.setProfilePhoto(fileName);

	    wrkrrep.save(worker);
	    return "Worker profile created successfully.";
	}
	public WrkrProfileDto getWorkerProfile(User user) {
		
	    Worker worker = wrkrrep.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Worker profile not found"));

	    WrkrProfileDto dto = new  WrkrProfileDto();

	    dto.setName(user.getName());
	    dto.setPhoneNumber(user.getPhoneNumber());
	    dto.setCity(worker.getCity());
	    dto.setDistrict(worker.getDistrict());
	    dto.setState(worker.getState());
	    dto.setDescription(worker.getDescription());
	    dto.setLanguages(worker.getLanguages());
	    List<String> professionNames = worker.getProfessions()
                .stream()
                .map(Profession::getName)
                .toList();

        dto.setProfessions(professionNames);
	    dto.setExperience(worker.getExperience());
	    dto.setRating(worker.getRating());
	    dto.setTotalJobs(worker.getTotalJobs());
	    dto.setAvailable(worker.getAvailable());
	    dto.setLatitude(worker.getLatitude());
	    dto.setLongitude(worker.getLongitude());
	    dto.setProfilePhoto(worker.getProfilePhoto());
	    dto.setCreatedAt(worker.getCreatedAt().toLocalDate());

	    return dto;
	}

	public ResponseEntity<List<WorkerCardDto>> allwrkrs() {

	    List<Worker> workers = wrkrrep.findAllWithUser();

	    List<WorkerCardDto> ans = new ArrayList<>();

	    for (Worker worker : workers) {
	    	if(!worker.getAvailable()) continue;
	        WorkerCardDto dto = new WorkerCardDto();

	        dto.setWorkerId(worker.getId());
	        dto.setName(worker.getUser().getName());
	        dto.setExperience(worker.getExperience());
	        dto.setRating(worker.getRating());
	        dto.setTotalJobs(worker.getTotalJobs());
	        dto.setProfilePhoto(worker.getProfilePhoto());

	        List<String> professionNames = worker.getProfessions()
	                .stream()
	                .map(Profession::getName)
	                .toList();
	        
	        dto.setProfession(professionNames);

	        ans.add(dto);
	    }

	    return ResponseEntity.ok(ans);
	}
	public List<WorkerCardDto> getNearbyWorkers(double lat,double lon, String profession, LocalDate workDate){

	    List<Worker> workers = wrkrrep.findAllWithUser();

	    List<WorkerCardDto> ans = new ArrayList<>();

	    for(Worker worker : workers){

	    	if (requestRepo.existsByWorkerAndWorkDateAndStatus(
	    	        worker,
	    	        workDate,
	    	        RequestStatusEnum.ACCEPTED)) {

	    	    continue;
	    	}

	        double distance = calculateDistance(
	                lat,
	                lon,
	                worker.getLatitude(),
	                worker.getLongitude());

	        if(distance <= 20){
	        	if (profession != null
	        	        && !profession.isBlank()
	        	        && worker.getProfessions().stream()
	        	            .noneMatch(p -> p.getName().equalsIgnoreCase(profession))) {
	        	    continue;
	        	}

	            WorkerCardDto dto = new WorkerCardDto();
	            dto.setWorkerId(worker.getId());
	            dto.setName(worker.getUser().getName());
	            dto.setExperience(worker.getExperience());

	            List<String> professionNames = worker.getProfessions()
	                    .stream()
	                    .map(Profession::getName)
	                    .toList();

	            dto.setProfession(professionNames);
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

	public ResponseEntity<OwnrWrkrProfileDto> getIdWrkr(Long id) {
		Optional<Worker> worker = wrkrrep.findByIdWithUser(id);
		if(worker.isEmpty()) return ResponseEntity.notFound().build();
		Worker n = worker.get();
		OwnrWrkrProfileDto wrkr = new OwnrWrkrProfileDto();
		wrkr.setAvailable(n.getAvailable());
		wrkr.setCity(n.getCity());
		wrkr.setCreatedAt(n.getCreatedAt().toLocalDate());
		wrkr.setDescription(n.getDescription());
		wrkr.setDistrict(n.getDistrict());
		wrkr.setExperience(n.getExperience());
		wrkr.setLanguages(n.getLanguages());
		wrkr.setName(n.getUser().getName());
		List<String> professionNames = n.getProfessions()
                .stream()
                .map(Profession::getName)
                .toList();

        wrkr.setProfessions(professionNames);
        wrkr.setProfilePhoto(n.getProfilePhoto());
        wrkr.setRating(n.getRating());
        wrkr.setState(n.getState());
        wrkr.setTotalJobs(n.getTotalJobs());
        return ResponseEntity.ok(wrkr);
	}

}

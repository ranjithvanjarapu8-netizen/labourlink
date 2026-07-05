package com.ranji.labourlink.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ranji.labourlink.Model.Job;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Repository.JobRepo;
import com.ranji.labourlink.dto.JobListDto;
import com.ranji.labourlink.dto.JobPostDto;

@Service
public class JobServ {
	@Autowired
	private JobRepo jobrep;
	
	public String createJob(User user, JobPostDto dto) {
		Job job = new Job();

		job.setOwner(user);

		job.setProfession(dto.getProfession());

		job.setTitle(dto.getTitle());

		job.setDescription(dto.getDescription());

		job.setWage(dto.getWage());

		job.setWorkersRequired(dto.getWorkersRequired());

		job.setAddress(dto.getAddress());

		job.setLatitude(dto.getLatitude());

		job.setLongitude(dto.getLongitude());

		job.setWorkDate(dto.getWorkDate());

		job.setStartTime(dto.getStartTime());

		job.setEndTime(dto.getEndTime());
		jobrep.save(job);

		return "Job Posted Successfully";
	}

	public ResponseEntity<List<JobListDto>> jobs() {
		List<JobListDto> alljobs = jobrep.findAllJobs();
		return ResponseEntity.ok(alljobs);
	}

}

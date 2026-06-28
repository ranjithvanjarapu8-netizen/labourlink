package com.ranji.labourlink.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.Job;
import com.ranji.labourlink.dto.JobListDto;

@Repository
public interface JobRepo extends JpaRepository<Job,Long>{

	@Query("""
		       SELECT new com.ranji.labourlink.dto.JobListDto(
		       		j.id,
		            j.profession,
		            j.title,
		            j.wage,
		            j.address,
		            j.workDate
		       )
		       FROM Job j
		       """)
		List<JobListDto> findAllJobs();

}

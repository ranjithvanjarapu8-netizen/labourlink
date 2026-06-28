package com.ranji.labourlink.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.dto.WorkerCardDto;
@Repository
public interface WorkerRepo extends JpaRepository<Worker,Long>{
	boolean existsByUser(User user);
	
	Optional<Worker> findByUser(User user);

	@Query("""
		       SELECT new com.ranji.labourlink.dto.WorkerCardDto(
		       		j.id,
		       		j.user.name,
		            j.profession,
		            j.experience,
		            j.rating,
		            j.totalJobs,
		            j.profilePhoto
		       )
		       FROM Worker j
		       """)
	List<WorkerCardDto> findAllDto();
	
	@Query("""
			SELECT w
			FROM Worker w
			JOIN FETCH w.user
			""")
			List<Worker> findAllWithUser();

}	

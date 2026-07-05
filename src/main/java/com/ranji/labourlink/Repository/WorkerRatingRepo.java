package com.ranji.labourlink.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.WorkRequest;
import com.ranji.labourlink.Model.WorkerRating;

@Repository
public interface WorkerRatingRepo extends JpaRepository<WorkerRating,Long>{

	Optional<WorkerRating> findByRequest(WorkRequest request);

}

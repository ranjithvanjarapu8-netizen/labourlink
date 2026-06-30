package com.ranji.labourlink.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ranji.labourlink.Model.RequestStatusEnum;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.WorkRequest;
import com.ranji.labourlink.Model.Worker;

public interface WrkRequestRepo extends JpaRepository<WorkRequest,Long>{

	@Query("""
		    SELECT wr
		    FROM WorkRequest wr
		    JOIN FETCH wr.owner
		    JOIN FETCH wr.profession
		    WHERE wr.worker = :worker
		      AND wr.status = :status
		    ORDER BY wr.createdAt DESC
		""")
		List<WorkRequest> findByWorkerAndStatusOrderByCreatedAtDesc(
		        @Param("worker") Worker worker,
		        @Param("status") RequestStatusEnum status);

	@Query("""
		       SELECT wr
		       FROM WorkRequest wr
		       WHERE wr.owner = :owner
		       ORDER BY wr.createdAt DESC
		       """)
		List<WorkRequest> findByOwnerOrderByCreatedAtDesc(
		        @Param("owner") User owner);

	@Query("""
		       SELECT wr
		       FROM WorkRequest wr
		       WHERE wr.id = :requestId
		       AND wr.worker = :worker
		       """)
		Optional<WorkRequest> findByIdAndWorker(
		        @Param("requestId") Long requestId,
		        @Param("worker") Worker worker);
	@Query("""
			SELECT wr
			FROM WorkRequest wr
			JOIN FETCH wr.owner
			JOIN FETCH wr.worker w
			JOIN FETCH w.user
			JOIN FETCH wr.profession
			WHERE wr.id = :requestId
			""")
			Optional<WorkRequest> findByIdWithDetails(@Param("requestId") Long requestId);

	@Query("""
		    SELECT wr
		    FROM WorkRequest wr
		    WHERE wr.worker = :worker
		""")
		List<WorkRequest> findByWorker(@Param("worker") Worker worker);
	
	@Query("""
		    SELECT COUNT(wr)
		    FROM WorkRequest wr
		    WHERE wr.worker = :worker
		      AND wr.status = :status
		""")
		long countByWorkerAndStatus(
		        @Param("worker") Worker worker,
		        @Param("status") RequestStatusEnum status);

	boolean existsByWorkerAndStatus(Worker worker, RequestStatusEnum accepted);
}

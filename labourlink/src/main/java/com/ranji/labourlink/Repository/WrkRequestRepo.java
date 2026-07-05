package com.ranji.labourlink.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ranji.labourlink.Model.RequestStatusEnum;
import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.WorkRequest;
import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.dto.OwnerRequestDto;

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

	@Query("""
		SELECT new com.ranji.labourlink.dto.OwnerRequestDto(
		    wr.id,
		    w.id,
		    u.name,
		    u.phoneNumber,
		    w.profilePhoto,
		    p.name,
		    wr.title,
		    wr.address,
		    wr.workDate,
		    wr.startTime,
		    wr.endTime,
		    wr.status
		)
		FROM WorkRequest wr
		JOIN wr.worker w
		JOIN w.user u
		JOIN wr.profession p
		WHERE wr.owner.id = :ownerId
		""")
		List<OwnerRequestDto> findOwnerRequests(@Param("ownerId") Long ownerId);

	boolean existsByWorkerAndWorkDateAndStatus(Worker worker, LocalDate workDate, RequestStatusEnum accepted);

	@Query("""
		    SELECT wr
		    FROM WorkRequest wr
		    JOIN FETCH wr.worker w
		    JOIN FETCH w.user
		    JOIN FETCH wr.profession
		    WHERE wr.owner = :owner
		      AND wr.status = :status
		    ORDER BY wr.createdAt DESC
		""")
		List<WorkRequest> findCompletedRequestsWithWorker(
		        @Param("owner") User owner,
		        @Param("status") RequestStatusEnum status
		);
}

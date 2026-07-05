package com.ranji.labourlink.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.User;
import com.ranji.labourlink.Model.Worker;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Long> {

    boolean existsByUser(User user);

    @Query("""
        SELECT DISTINCT w
        FROM Worker w
        LEFT JOIN FETCH w.workerProfessions wp
        LEFT JOIN FETCH wp.profession
        WHERE w.user = :user
    """)
    Optional<Worker> findByUser(@Param("user") User user);

    @Query("""
        SELECT DISTINCT w
        FROM Worker w
        JOIN FETCH w.user
        LEFT JOIN FETCH w.workerProfessions wp
        LEFT JOIN FETCH wp.profession
    """)
    List<Worker> findAllWithUser();

    @Query("""
        SELECT DISTINCT w
        FROM Worker w
        LEFT JOIN FETCH w.user
        LEFT JOIN FETCH w.workerProfessions wp
        LEFT JOIN FETCH wp.profession
        WHERE w.id = :id
    """)
    Optional<Worker> findByIdWithUser(@Param("id") Long id);

}
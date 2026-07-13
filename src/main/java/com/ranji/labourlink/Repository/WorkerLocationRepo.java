package com.ranji.labourlink.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Model.WorkerLocation;

@Repository
public interface WorkerLocationRepo extends JpaRepository<WorkerLocation, Long> {

    Optional<WorkerLocation> findByWorker(Worker worker);

}
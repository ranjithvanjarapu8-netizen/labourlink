package com.ranji.labourlink.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.Worker;
import com.ranji.labourlink.Model.WorkerProfession;

@Repository
public interface WorkerProfessionRepo extends JpaRepository<WorkerProfession, Long> {

    List<WorkerProfession> findByWorker(Worker worker);

}
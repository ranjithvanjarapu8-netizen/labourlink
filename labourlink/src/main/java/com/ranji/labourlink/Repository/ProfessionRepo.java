package com.ranji.labourlink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.Profession;
@Repository
public interface ProfessionRepo extends JpaRepository<Profession,Long>{

}

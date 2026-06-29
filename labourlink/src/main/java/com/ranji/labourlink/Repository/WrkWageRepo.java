package com.ranji.labourlink.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.Profession;
import com.ranji.labourlink.Model.WrkWage;
@Repository
public interface WrkWageRepo extends JpaRepository<WrkWage,Long>{
	List<WrkWage> findByProfession(Profession profession);
}

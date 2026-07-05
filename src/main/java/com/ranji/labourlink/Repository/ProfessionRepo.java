package com.ranji.labourlink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ranji.labourlink.Model.Profession;
@Repository
public interface ProfessionRepo extends JpaRepository<Profession,Long>{

	@Query("SELECT a FROM Profession AS a WHERE a.name =:profession")
	Profession findByProfession(@Param("profession") String profession);

}

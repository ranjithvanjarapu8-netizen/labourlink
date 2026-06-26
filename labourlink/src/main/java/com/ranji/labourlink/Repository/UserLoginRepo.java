package com.ranji.labourlink.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ranji.labourlink.Model.User;

public interface UserLoginRepo extends JpaRepository<User,Integer>{
	
	@Query("SELECT u FROM User AS u WHERE u.phoneNumber = :phno")
	User findByphno(@Param("phno") String phno);


}

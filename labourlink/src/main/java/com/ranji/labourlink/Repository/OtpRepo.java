package com.ranji.labourlink.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ranji.labourlink.Model.OtpVerification;

public interface OtpRepo extends JpaRepository<OtpVerification,Long>{

	@Query("SELECT a FROM OtpVerification AS a WHERE a.phoneNumber=:phoneNumber")
	 Optional<OtpVerification> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}

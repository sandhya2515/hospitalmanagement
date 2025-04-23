package com.simple.patientapp.repository;

import com.simple.patientapp.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long>  {
	 
	List<Availability> findByDoctorUid(String doctorUid);
    List<Availability> findByDoctorUidAndAvailableDate(String doctorUid, LocalDate availableDate);

	
	//List<Availability>  ;

}

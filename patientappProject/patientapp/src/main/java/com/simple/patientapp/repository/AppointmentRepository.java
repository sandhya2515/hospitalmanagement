package com.simple.patientapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.simple.patientapp.model.Appointments;

public interface AppointmentRepository extends JpaRepository<Appointments, Integer> {

	    
	List<Appointments> findByPuId(String puId);
	
    int countByPuId(String PuId);
    
   // List<Appointments> findByPatientId(String PuId);

    //List<Appointments> findByPatientName(String patientName);

    //List<Appointments> findByAppointmentDate(String appointment_date);
    
	 List<Appointments> findBydoctorUID(String doctorUID); 
	 
	 Optional<Appointments> findById(Long id);    
}
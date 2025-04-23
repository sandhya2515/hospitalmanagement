package com.simple.doctor.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.repository.DoctorRepository;

@Service
public class DoctorLoginService {
	
	 @Autowired
	    private DoctorRepository doctorRepo;

	    // Method to find Doctor by primary email
	    public Doctor findByPrimaryEmail(String primaryEmail) {
	        return doctorRepo.findByPrimaryEmail(primaryEmail);
	    }

	    // Method to authenticate doctor
	    public boolean authenticateDoctor(String primaryEmail, String password) {
	        Doctor doctor = findByPrimaryEmail(primaryEmail);
	        return doctor != null && doctor.getPassword().equals(password);
	    }

}

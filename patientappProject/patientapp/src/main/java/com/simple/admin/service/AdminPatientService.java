package com.simple.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Patient;
import com.simple.patientapp.repository.PatientRepository;


@Service
public class AdminPatientService {
	
	@Autowired
	private PatientRepository patientrepository;
	
	
	public List<Patient> getAllPatient(){
		
		return patientrepository.findAll();
		
	}

}

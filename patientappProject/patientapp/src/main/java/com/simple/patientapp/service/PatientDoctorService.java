package com.simple.patientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.repository.DoctorRepository;

@Service
public class PatientDoctorService {
	
	@Autowired
    private DoctorRepository doctorRepository;
    public List<Doctor> getAllFirstname() {
        return doctorRepository.findAll();
    }

}
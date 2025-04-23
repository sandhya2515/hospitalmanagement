package com.simple.admin.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.repository.DoctorRepository;

@Service
public class DoctorService {
	
	
	@Autowired
	private DoctorRepository doctorrepository;
	
	public void SaveDoctor(Doctor doctor) {
        // Generate Doctor UID if it's not already present
        if (doctor.getDoctorUID() == null || doctor.getDoctorUID().isEmpty()) {
            String DoctorUID = generatedoctorUID();
            doctor.setDoctorUID(DoctorUID);
        }

        // Generate Password if it's not already set
        if (doctor.getPassword() == null || doctor.getPassword().isEmpty()) {
            String password = generatePassword(doctor);
            doctor.setPassword(password);
        }

        System.out.println("Saving doctor: " + doctor);
        doctorrepository.save(doctor);
    }
    
    public List<Doctor> getAllDoctors() {
        return doctorrepository.findAll();
    }
    
    public Doctor getdoctorbyId(Long doctorId) {
        return doctorrepository.findById(doctorId).orElse(null);
    }
    
    public Doctor Updatedoctor(Doctor doctor) {
        return doctorrepository.save(doctor);
    }
    
    public Doctor deleteDoctorById(Long doctorId) {
        doctorrepository.deleteById(doctorId);
        return null;
    }
    
    // Generates a unique Doctor UID
    private String generatedoctorUID() {
        int length = 8;  // Length of the UID (for example 8 characters)
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder drId = new StringBuilder(length);

        // Generate random characters for the UID
        for (int i = 0; i < length; i++) {
            drId.append(characters.charAt(random.nextInt(characters.length())));
        }

        return "DR" + drId.toString();
    }
    
    // Generates a password for the doctor using first name and date of birth
    private String generatePassword(Doctor doctor) {
        // Safely get the first 4 characters from the first name
        String firstName = doctor.getFirstName();
        String characters = firstName.length() >= 4 ? firstName.substring(0, 4).toUpperCase() : firstName.toUpperCase();
        
        // Get the year from the date of birth
        int dobYear = doctor.getDateOfBirth().getYear() + 1900;  // Add 1900 if using Date class
        
        // Create password by combining firstName characters and birth year
        String password = characters + "@" + dobYear;
        
        return password;
    }

	public void SavenewDoctor(Doctor existingDoctor) {
		
	doctorrepository.save(existingDoctor);
		
	}

	

}

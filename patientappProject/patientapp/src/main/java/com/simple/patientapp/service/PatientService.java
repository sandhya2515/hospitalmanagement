package com.simple.patientapp.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Appointments;
import com.simple.patientapp.model.Patient;
import com.simple.patientapp.repository.PatientRepository;

@Service
public class PatientService {

@Autowired	
private PatientRepository patientRepository;

//save patient
public void savePatient(Patient patient) {
    if (patient.getPuId() == null || patient.getPuId().isEmpty()) {
        String puId = generatePuId();
        patient.setPuId(puId);
    }
    System.out.println("Saving patient: " + patient);
    patientRepository.save(patient);
}

public Patient findByContact(String contactNumber) {
    return patientRepository.findByContact(contactNumber);
}

public Patient findByEmail( String email) {
	return patientRepository.findByEmail(email);
}

//Check patient Username and Password Correct or not
public boolean authenticatePatient(String emailOrContact, String password) {
    Patient patient = null;
    // Check if the input contains an '@', meaning it's an email
    if (emailOrContact.contains("@")) {
        patient = patientRepository.findByEmail(emailOrContact);
    } else {
        // If it doesn't contain '@', treat it as a contact number
        patient = patientRepository.findByContact(emailOrContact);
    }

    // Check if patient exists and password matches
    if (patient != null && patient.getPassword().equals(password)) {
        return true;
    }
    return false;
}

public Patient findByEmailOrContact(String emailOrContact) {
    if (emailOrContact.contains("@")) {
        return patientRepository.findByEmail(emailOrContact);
    } else {
        return patientRepository.findByContact(emailOrContact);
    }
}

private String generatePuId() {
    int length = 8;  // Length of the PuId (for example 8 characters)
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Random random = new Random();
    StringBuilder puId = new StringBuilder(length);

    // Generate random characters for the PuId
    for (int i = 0; i < length; i++) {
        puId.append(characters.charAt(random.nextInt(characters.length())));
    }

     return "PA" + puId.toString();
}


public Patient getPatientByPuId(String puId) { //get data from DB by puid
    return patientRepository.findByPuId(puId);
}

public void updatePatientByPuId(String PuId, Patient updatedPatient) {
    Patient patient = patientRepository.findByPuId(PuId);
    if (patient == null) {
        throw new RuntimeException("Patient with PuId not found.");
    }
    
    // Update fields that need to be updated
    patient.setEmail(updatedPatient.getEmail());
    patient.setContact(updatedPatient.getContact());
    patient.setAlternateContact(updatedPatient.getAlternateContact());
    patient.setPermanentAddress(updatedPatient.getPermanentAddress());
    patient.setTemporaryAddress(updatedPatient.getTemporaryAddress());
    
    // Save updated patient
    patientRepository.save(patient);
}

	
}

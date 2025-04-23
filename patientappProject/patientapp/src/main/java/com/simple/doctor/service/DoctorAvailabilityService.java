package com.simple.doctor.service;

import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Availability;
import com.simple.patientapp.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
@Service
public class DoctorAvailabilityService {
	
	@Autowired
	private AvailabilityRepository availabilityRepository;

	// Save a new availability record
	public Availability saveAvailability(Availability availability) {
		return availabilityRepository.save(availability); // Save the availability object to the database
	}

	// Fetch all availability records
	public List<Availability> getAllAvailability() {
		// Return all the records from the availability repository
		return availabilityRepository.findAll();
	}

	// Update an existing availability record by its ID
	public Availability updateAvailability(Long id, Availability availability) {
		// Check if the availability record with the given ID exists
		if (availabilityRepository.existsById(id)) {
			availability.setId(id); // Set the ID of the existing record to be updated
			return availabilityRepository.save(availability); // Save the updated availability
		} else {
			throw new RuntimeException("Availability record not found");
		}
	}

	// Delete an availability record by its ID
	public void deleteAvailability(Long id) {
		// Check if the record exists before attempting to delete it
		if (availabilityRepository.existsById(id)) {
			availabilityRepository.deleteById(id); // Delete the record
		} else {
			throw new RuntimeException("Availability record not found");
		}
	}

	
	  public List<Availability> getAvailabilityByDoctorUid(String doctorUid){
	  return availabilityRepository.findByDoctorUid(doctorUid); }
	  
	  
	  public List<Availability> getAvailabilityBydoctorUID(){ return
	  availabilityRepository.findAll(); }
	 
	

}

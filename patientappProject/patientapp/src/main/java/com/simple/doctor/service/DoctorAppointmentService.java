package com.simple.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Appointments;
import com.simple.patientapp.repository.AppointmentRepository;

@Service
public class DoctorAppointmentService {

    @Autowired
    private AppointmentRepository appointmentsRepository;

    // Method to save prescription details and update status
    public void savePrescriptionDetails(Long id, String prescription, String diagnosis, String notes) {
        // Find the appointment by ID
        Optional<Appointments> optionalAppointment = appointmentsRepository.findById(id);
        
        if (optionalAppointment.isPresent()) {
            Appointments appointment = optionalAppointment.get();

			/*
			 * // Update prescription, diagnosis, and notes
			 * appointment.setPrescription(prescription);
			 * appointment.setDiagnosis(diagnosis); appointment.setNotes(notes);
			 */
            // Set status to 'Pending' if not already set
            if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {
                appointment.setStatus("Pending");
            }

            // Change the status to 'Complete' after details are added
            appointment.setStatus("Complete");

            // Save the updated appointment
            appointmentsRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found with ID: " + id);
        }
    }

    public List<Appointments> getAppointmentsByDoctorUid(String doctorUID){
    	  return appointmentsRepository.findBydoctorUID(doctorUID); }
    	  
    	  
    	  public List<Appointments> getAppointmentsBydoctorUID(){ return
    			appointmentsRepository.findAll(); }
    	 
    // Method to get an appointment by ID
    public Appointments getAppointmentById(Long id) {
        return appointmentsRepository.findById(id)
                                     .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

    // Method to get patient history by patient ID
    public List<Appointments> getPatientHistoryByPatientId(String PuId) {
        return appointmentsRepository.findByPuId(PuId);
    }
}

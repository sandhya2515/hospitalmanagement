package com.simple.doctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.patientapp.model.Appointments;
//import com.simple.patientapp.model.Availability;
import com.simple.doctor.service.DoctorAppointmentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DoctorAppointmentController {
	
	 @Autowired
	    private DoctorAppointmentService appointmentService;

	    // Method to view all appointments
	    @GetMapping("/viewappointments")
	    public String viewAppointments(Model model,HttpSession session) {
	    	
	    	String doctorUid = (String) session.getAttribute("doctorUID");
	        if (doctorUid != null) {
	        	List<Appointments> appointments = appointmentService.getAppointmentsByDoctorUid(doctorUid);
	        			
	        	 if (appointments.isEmpty()) {
	        		 
	              model.addAttribute("message", "No appointments found."); // Handle case where no data is available          }
	        	        	
	        	 }
      		   
	        	 model.addAttribute("appointments", appointments);        		   


	        return "/Doctor/doctorviewappointments"; // Thymeleaf template for viewing appointments
	    }
	        return "/Doctor/doctordashboard";
	    }
	    

	    // Method to show the form to add a prescription
	    @GetMapping("/addprescription/{id}")
	    public String showAddPrescriptionForm(@PathVariable Long id, Model model) {
	        Appointments appointment = appointmentService.getAppointmentById(id);
	        if (appointment != null) {
	            model.addAttribute("appointment", appointment);
	        } else {
	            model.addAttribute("message", "Appointment not found.");
	        }
	        return "/Doctor/addprescription"; // Thymeleaf template name
	    }

	    // Method to save prescription details
	    @PostMapping("/appointments/save-details")
	    public ResponseEntity<Map<String, Object>> saveDetails(@RequestParam Long id,
	                                                           @RequestParam String prescription,
	                                                           @RequestParam String diagnosis,
	                                                           @RequestParam String notes) {
	        Map<String, Object> response = new HashMap<>();
	        try {
	            // Call the service method to save the prescription details
	            appointmentService.savePrescriptionDetails(id, prescription, diagnosis, notes);
	            
	            // Add success response
	            response.put("success", true);
	            response.put("message", "Details saved successfully.");
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            // Log the exception
	            e.printStackTrace();

	            // Add failure response
	            response.put("success", false);
	            response.put("message", "Failed to save details. Please try again.");
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }

	    // Method to view patient history based on patient ID
	    @GetMapping("/patienthistory/{id}")
	    public String viewPatientHistory(@PathVariable("id") String PuId, Model model) {
	        List<Appointments> patientHistory = appointmentService.getPatientHistoryByPatientId(PuId);
	        if (patientHistory.isEmpty()) {
	            model.addAttribute("message", "No history found for this patient.");
	        } else {
	            model.addAttribute("patientHistory", patientHistory);
	        }
	        return "/Doctor/patienthistory"; // Maps to Thymeleaf template patienthistory.html
	    }
	    

}

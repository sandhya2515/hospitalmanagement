package com.simple.doctor.controller;

import com.simple.patientapp.model.Availability;
import com.simple.doctor.service.DoctorAvailabilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class doctorAvailabilityController {

    @Autowired
    private DoctorAvailabilityService availabilityService;

    // Manage availability page
    @GetMapping("/availability")
    public String manageAvailability(Model model, HttpSession session) {
        model.addAttribute("availabilityData", new Availability()); // Create a new Availability object
        
        String doctorUid = (String) session.getAttribute("doctorUID");
        if (doctorUid != null) {
        	List<Availability> availabilityData = availabilityService.getAvailabilityByDoctorUid(doctorUid);
        			
        		   model.addAttribute("availability", availabilityData);
        	
        	}
        return "/Doctor/availability"; // Return the Thymeleaf template name (availability.html)
    }

    // Save a new availability record
    @PostMapping("/availability")
    public String saveAvailability(@ModelAttribute Availability availabilityData, HttpSession session, Model model) {
        String doctorUID = (String) session.getAttribute("doctorUID");

        // Check if doctorUID is available in session
        if (doctorUID != null) {
            availabilityData.setDoctorUid(doctorUID); // Set the doctor UID
            availabilityService.saveAvailability(availabilityData);

            // Fetch the updated list of availability records
            model.addAttribute("availabilityList", availabilityService.getAllAvailability());
            model.addAttribute("availabilityData", new Availability()); // Clear the form for next input
            model.addAttribute("successMessage", "Availability saved successfully."); // Success message

            return "/Doctor/availability"; // Return the same Thymeleaf template to show updated data
        } else {
            model.addAttribute("errorMessage", "Doctor UID not found in session.");
            return "/Doctor/availability"; // Return the same Thymeleaf template with an error message
        }
    }
    
    

}

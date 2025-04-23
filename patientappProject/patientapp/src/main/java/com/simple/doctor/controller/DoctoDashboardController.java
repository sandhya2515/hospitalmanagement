package com.simple.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.patientapp.model.Appointments;
import com.simple.patientapp.model.Availability;
import com.simple.doctor.service.DoctorAppointmentService;
import com.simple.doctor.service.DoctorAvailabilityService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DoctoDashboardController {
	
    @Autowired
    private DoctorAppointmentService appointmentService;
    @Autowired
    private DoctorAvailabilityService availabilityService;

   
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "/Doctor/doctordashboard"; // Thymeleaf template for dashboard
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	if (session != null) {
         session.invalidate();

        return "/Doctor/doctorlogin";
    }
		return "Doctor/doctordashboard";
    	
    }
    

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "/Doctor/doctorlogin"; // Thymeleaf template for logout success
    }

}

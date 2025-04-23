package com.simple.patientapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.patientapp.model.Department;
import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.repository.DoctorRepository;
import com.simple.patientapp.service.PatientDepartmentService;
import com.simple.patientapp.service.PatientDoctorService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/doctors")
public class PatientDoctorController {
	
		@Autowired
	    private DoctorRepository doctorRepository;

		@Autowired
	    private PatientDepartmentService departmentService;
	    @Autowired
	    private PatientDoctorService doctorService;
		
	    
	     @GetMapping("/{firstName}")
	     public String getDoctorPage(@PathVariable("firstName") String name, Model model) {

	    	 Doctor doctor = doctorRepository.findByFirstName(name);
	         List<Department> departments = departmentService.getAllDepartments(); 
	         List<Doctor> doctors = doctorService.getAllFirstname();


	        if (doctor != null) {
	            // Add doctor details to the model
	            model.addAttribute("doctor", doctor);
	            model.addAttribute("departments", departments);
	            model.addAttribute("doctors", doctors);

	            return "/Patient/doctor-page"; 
	        } else {
	            return "error"; 
	        }
	    }
	
	
	     @GetMapping("/checkSession")
	     @ResponseBody
	     public Map<String, Boolean> checkSession(HttpSession session) {
	         Map<String, Boolean> response = new HashMap<>();
	         response.put("loggedIn", session.getAttribute("loggedInUser") != null);
	         return response;
	     }
	
	

}


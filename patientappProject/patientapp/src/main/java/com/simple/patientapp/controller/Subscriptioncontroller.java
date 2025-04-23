package com.simple.patientapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simple.patientapp.model.Department;
import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.service.PatientDepartmentService;
import com.simple.patientapp.service.PatientDoctorService;
import com.simple.patientapp.service.PatientPackageService;

@Controller
public class Subscriptioncontroller {

	   @Autowired
	    private PatientPackageService packageService;
	    @Autowired
	    private PatientDepartmentService departmentService;
	    @Autowired
	    private PatientDoctorService doctorService;

	    @GetMapping("/buypackage")
	    public String listPackages(Model model) {
	    	List<Department> departments = departmentService.getAllDepartments();
	        List<Doctor> doctors = doctorService.getAllFirstname();
	        model.addAttribute("departments", departments);
	        model.addAttribute("doctors", doctors);
	        // Add packages from the service to the model
	        model.addAttribute("packages", packageService.getAllPackages());
	        
	       
	        return "/Patient/buypackage"; 
	    }
}


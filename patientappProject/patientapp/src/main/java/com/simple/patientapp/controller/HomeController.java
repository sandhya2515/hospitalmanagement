package com.simple.patientapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.patientapp.model.Appointments;
import com.simple.patientapp.model.Department;
import com.simple.patientapp.service.PatientDepartmentService;
import com.simple.patientapp.service.PatientDoctorService;
import com.simple.patientapp.service.PatientService;
import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.model.Patient;
import com.simple.patientapp.service.PatientDoctorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
   @RequestMapping("/")
   public String showHomePage(Model model) {
       // Fetching department data from the database
       List<Department> departments = departmentService.getAllDepartments();
       List<Doctor> doctors = doctorService.getAllFirstname();

       
       model.addAttribute("departments", departments);
       model.addAttribute("doctors", doctors);

        return "home"; 
    }
    
    @GetMapping("/registration")
    public String ManageRegister() 
    {
		return "index";
    	
   }
   
    @GetMapping("/orthopedics")
    public String shwoProfile() 
    {
		return "orthopedics";
    	
   }
   
    
    
    @GetMapping("/login")
    public String ManageLogin() 
    {
		return "home";
    	
   }
    
    @GetMapping("/updateprofile")
    public String updateProfile(Model model, HttpSession session) {
        List<Department> departments = departmentService.getAllDepartments();
        List<Doctor> doctors = doctorService.getAllFirstname();
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);
        String patientPuId = (String) session.getAttribute("PaUid");
        if (patientPuId != null) {
        
        	Patient patient=patientService.getPatientByPuId(patientPuId);
        	model.addAttribute("patient", patient);

        }

        return "/Patient/updateprofile"; // Thymeleaf template
    }
    
    @GetMapping("/appointmentform")
    public String AppointmentForm() {
		return "appointmentform";      
        
   }
    
    
    
    
    @GetMapping("/returntohome")
    public String ReturnToHome() 
    {
		return "home";
    	
   }


    @GetMapping("/patientlogout")
    public String logout(HttpSession session) {
    	if (session != null) {
    		
        session.invalidate();
        return "redirect:/"; 
    	}
        return "redirect:/patients/login"; 
    }
    

    @GetMapping("/checkSession")
    @ResponseBody
    public Map<String, Boolean> checkSession(HttpSession session) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("loggedIn", session.getAttribute("loggedInUser") != null);
        return response;
    }

    
    
    

    @Autowired
    private PatientDepartmentService departmentService;
    @Autowired
    private PatientDoctorService doctorService;
    @Autowired
    private PatientService patientService;

    
  
    
    
    
    
   
	
}
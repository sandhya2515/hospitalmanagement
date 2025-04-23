package com.simple.patientapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.patientapp.model.Patient;
import com.simple.patientapp.service.PatientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/patients")
public class PatientController {

	 @Autowired
	    private PatientService patientService;

	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("patient", new Patient());
	        return "index";
	    }
	    
	    @PostMapping("/register")
	    public String registerPatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {
	       
	        Patient existingEmail = patientService.findByEmail(patient.getEmail());
	        
	        if ( existingEmail != null) {
	            
	            redirectAttributes.addFlashAttribute("message", "Patient already exists!");
	            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
	            return "redirect:/registration";  // Redirect back to the registration page
	        } 
	        
	        Patient existingContact = patientService.findByContact(patient.getContact());
	        if (existingContact != null) {
	            redirectAttributes.addFlashAttribute("message", "Patient with this contact number already exists!");
	            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
	            return "redirect:/registration";  // Redirect back to the registration page
	        }
	        
	        
	        patientService.savePatient(patient);
	        redirectAttributes.addFlashAttribute("message", "Patient saved successfully!");
	        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	        return "redirect:/patients/login";  
	    }


	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "/Patient/login";
	    }

	    @PostMapping("/login")
	    public String loginPatient(@RequestParam("emailOrContact") String emailOrContact,
	                               @RequestParam("password") String password, 
	                               Model model, 
	                               HttpSession session) {
	        
	        boolean isAuthenticated = patientService.authenticatePatient(emailOrContact, password);

	        if (isAuthenticated) {
	            // Store user details in session
	            Patient loggedInPatient = patientService.findByEmailOrContact(emailOrContact); // Fetch the full Patient object

	            session.setAttribute("loggedInUser", loggedInPatient.getFirstname());
	            session.setAttribute("PaUid", loggedInPatient.getPuId());

	            return "redirect:/"; // Redirect to the home page or dashboard
	        } else {
	            model.addAttribute("loginError", "Invalid email/contact or password");
	            return "/Patient/login";
	        }
	    }

	    @PostMapping("/update")
	    public String updatePatient(@ModelAttribute("patient") Patient patient, HttpSession session) {
	        String patientPuId = (String) session.getAttribute("PaUid");

	        if (patientPuId != null) {
	            Patient existingPatient = patientService.getPatientByPuId(patientPuId);

	            existingPatient.setFirstname(patient.getFirstname());
	            existingPatient.setMiddlename(patient.getMiddlename());
	            existingPatient.setLastname(patient.getLastname());
	            existingPatient.setGender(patient.getGender());
	            existingPatient.setAge(patient.getAge());
	            existingPatient.setContact(patient.getContact());
	            existingPatient.setAlternateContact(patient.getAlternateContact());
	            existingPatient.setEmail(patient.getEmail());
	            existingPatient.setPermanentAddress(patient.getPermanentAddress());
	            existingPatient.setTemporaryAddress(patient.getTemporaryAddress());

	            patientService.updatePatientByPuId(patientPuId, patient);
	            return "redirect:/";
	        } else {
	            return "redirect:/login"; 
	        }
	    

	
	    
	    

	    }
}
	    
	    // Additional methods to handle login can be added here
	


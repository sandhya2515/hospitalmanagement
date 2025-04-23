
	package com.simple.patientapp.controller;

	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;

import com.simple.patientapp.model.Department;
import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.model.SupportTicket;
import com.simple.patientapp.service.PatientDepartmentService;
import com.simple.patientapp.service.PatientDoctorService;
import com.simple.patientapp.service.PatientSupportTicketService;

import jakarta.servlet.http.HttpSession;

	@Controller
	public class PatientSupportTicketController {
	    
	    @Autowired
	    private PatientSupportTicketService ticketService;
	    @Autowired
	    private PatientDepartmentService departmentService;
	    @Autowired
	    private PatientDoctorService doctorService;

	    // Show the form to create a new ticket
	    @GetMapping("/create-ticket")
	    public String showCreateTicketForm(Model model) {
	    	List<Department> departments = departmentService.getAllDepartments();
	        List<Doctor> doctors = doctorService.getAllFirstname();
	        model.addAttribute("departments", departments);
	        model.addAttribute("doctors", doctors);
	    	
	        model.addAttribute("ticket", new SupportTicket());

	        return "/Patient/create-ticket"; // Make sure this is the correct template name
	    }

	    // Handle the form submission to create a new ticket
	    @PostMapping("/create-ticket")
	    public String createTicket(@ModelAttribute SupportTicket ticket, Model model,HttpSession session)
	    
	    {
	    	String patientPuId = (String) session.getAttribute("PaUid");	
	    	if (patientPuId != null) {
	    		ticket.setpuId(patientPuId);
	        ticketService.createTicket(ticket);
	        model.addAttribute("message", "Ticket Send successfully!");
	        return "redirect:/";
	    	
	    	}
	    	else {
	    		 model.addAttribute("message", "Error: No logged-in patient found.");
	    		   
	    		 return "redirect:/Patient/create-ticket"; // Redirect to the list of tickets after creation
	    	}
	    		     
	    }

	    
	 // Change the path to avoid conflict
	    @GetMapping("/view-ticket")
	    public String listTickets(Model model, HttpSession session) {
	        String patientPuId = (String) session.getAttribute("PaUid");
	        if (patientPuId != null) {
	            List<SupportTicket> tickets = ticketService.getTicketsByPatientPuId(patientPuId);
	            List<Department> departments = departmentService.getAllDepartments();
	            List<Doctor> doctors = doctorService.getAllFirstname();
	            model.addAttribute("departments", departments);
	            model.addAttribute("doctors", doctors); 
	            model.addAttribute("tickets", tickets);
	        } else {
	            model.addAttribute("error", "No tickets found for the patient.");
	        }
	        return "/Patient/ticketList"; // Updated template to show patient's tickets
	    }

	    
	    
	    
	    
	    
	}



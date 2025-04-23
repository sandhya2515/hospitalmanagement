package com.simple.doctor.controller;


import com.simple.patientapp.model.SupportTicket;
import com.simple.doctor.service.doctorSupportTicketService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class doctorSupportTicketController {
	

    @Autowired
    private doctorSupportTicketService supportTicketService;

    // Display the form to create a new support ticket
    @GetMapping("/supportticket")
    public String showNewTicketForm(Model model) {
        model.addAttribute("supportTicket", new SupportTicket());
        return "/Doctor/new_ticket"; // Thymeleaf template for creating a new ticket
    }

    // Handle form submission to create a new support ticket
    @PostMapping("/supportticket")
    public String saveSupportTicket(@ModelAttribute SupportTicket supportTicket, Model model,HttpSession session) {
        try {
            // Set default status to "Pending" and capture current timestamp
        	String DoctorUID=(String) session.getAttribute("doctorUID");
        	if (DoctorUID !=null) {
        		supportTicket.setDoctorUid(DoctorUID);
            supportTicket.setStatus("Pending");
            supportTicket.setCreatedAt(LocalDateTime.now());

            supportTicketService.createSupportTicket(supportTicket);
            model.addAttribute("message", "Ticket created successfully.");
            model.addAttribute("alertClass", "alert-success");
            model.addAttribute("supportTicket", new SupportTicket()); // Clear the form
        	}
        } catch (Exception e) {
            model.addAttribute("message", "Error creating ticket: " + e.getMessage());
            model.addAttribute("alertClass", "alert-danger");
        }
        return "/Doctor/new_ticket"; // Return to the form and display message
    }

    // Display all support tickets
    @GetMapping("/supportticket/list")
    public String listTickets(Model model) {
        List<SupportTicket> tickets = supportTicketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "/Doctor/ticket_list"; // Thymeleaf template to display ticket list
    }

    // View a specific ticket's details by ID
    @GetMapping("/supportticket/view/{id}")
    public String viewTicket(@PathVariable("id") Long id, Model model) {
        SupportTicket ticket = supportTicketService.getTicketById(id);
        if (ticket != null) {
            model.addAttribute("ticket", ticket);
            return "/Doctor/view_ticket"; // Thymeleaf template for viewing ticket details
        } else {
            model.addAttribute("message", "Invalid ticket ID: " + id);
            model.addAttribute("alertClass", "alert-danger");
            return "/Doctor/supportticket/list"; // Redirect to ticket list page
        }
    }

    // Handle ticket deletion
    @PostMapping("/supportticket/delete")
    public String deleteTicket(@RequestParam("id") Long id, Model model) {
        try {
            supportTicketService.deleteTicket(id);
            model.addAttribute("message", "Ticket deleted successfully.");
            model.addAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            model.addAttribute("message", "Error deleting ticket: " + e.getMessage());
            model.addAttribute("alertClass", "alert-danger");
        }
        return "redirect:/supportticket/list"; // Redirect to the ticket list after deletion
    }

    // Display the form to update an existing ticket
//    @GetMapping("/supportticket/edit/{id}")
//    public String showUpdateTicketForm(@PathVariable("id") Long id, Model model) {
//        SupportTicket ticket = supportTicketService.getTicketById(id);
//        if (ticket != null) {
//            model.addAttribute("supportTicket", ticket);
//            return "update_ticket"; // Thymeleaf template for editing a ticket
//        } else {
//            model.addAttribute("message", "Invalid ticket ID: " + id);
//            model.addAttribute("alertClass", "alert-danger");
//            return "redirect:/supportticket/list"; // Redirect to ticket list page
//        }
//    }

    // Handle form submission to update an existing support ticket
//    @PostMapping("/supportticket/update")
//    public String updateSupportTicket(@ModelAttribute SupportTicket supportTicket, Model model) {
//        try {
//            // Update the ticket details
//            supportTicketService.updateTicket(supportTicket);
//            model.addAttribute("message", "Ticket updated successfully.");
//            model.addAttribute("alertClass", "alert-success");
//        } catch (Exception e) {
//            model.addAttribute("message", "Error updating ticket: " + e.getMessage());
//            model.addAttribute("alertClass", "alert-danger");
//        }
//        return "redirect:/supportticket/list"; // Redirect to the ticket list after updating
//    }

}

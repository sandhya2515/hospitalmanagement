package com.simple.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.simple.patientapp.model.SupportTicket;
import com.simple.admin.service.SupportTicketService;

@Controller
public class SupportTicketController {

    @Autowired
    private SupportTicketService supportticketservice;

    @GetMapping("/updatestatusinprogress")
    public String updateStatusToInProgress(@RequestParam("id") Long id) {
        SupportTicket existingsupportticket = supportticketservice.getSupportTicketById(id); // Fetch existing ticket

        if (existingsupportticket != null) {
            existingsupportticket.setStatus("In Progress"); // Update status
            supportticketservice.SaveTicket(existingsupportticket); // Save updated ticket
        }

        return "redirect:/managedoctorsupportticket"; // Redirect after update
    }
    
    @GetMapping("/updatestatusissueresolved")
    public String updateStatusIssueResolved(@RequestParam("id") Long id) {
        SupportTicket existingsupportticket = supportticketservice.getSupportTicketById(id); // Fetch existing ticket

        if (existingsupportticket != null) {
            existingsupportticket.setStatus("Issue Resolved"); // Update status
            supportticketservice.SaveTicket(existingsupportticket); // Save updated ticket
        }

        return "redirect:/managedoctorsupportticket"; // Redirect after update
    }
}

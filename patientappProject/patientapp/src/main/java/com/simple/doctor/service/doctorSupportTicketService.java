package com.simple.doctor.service;

import com.simple.patientapp.model.SupportTicket;
import com.simple.patientapp.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class doctorSupportTicketService {
	
	@Autowired
    private SupportTicketRepository supportTicketRepository;

    // Save a new support ticket
    public SupportTicket createSupportTicket(SupportTicket supportTicket) {
        // Set created_at time and default status before saving
        supportTicket.setCreatedAt(LocalDateTime.now());
        supportTicket.setStatus("Pending");  // Default status
        return supportTicketRepository.save(supportTicket);
    }

    // Get all support tickets
    public List<SupportTicket> getAllTickets() {
        return supportTicketRepository.findAll();
    }

    // Get a specific support ticket by ID
    public SupportTicket getTicketById(Long id) {
        // Return null if the ticket is not found
        return supportTicketRepository.findById(id).orElse(null);
    }

    // Delete a support ticket by ID
    public void deleteTicket(Long id) {
        supportTicketRepository.deleteById(id);
    }

//    // Update an existing support ticket
//    public SupportTicket updateTicket(SupportTicket supportTicket) {
//        // Set updated_at field (assuming it's present in your entity)
//        supportTicket.setUpdatedAt(LocalDateTime.now());
//        return supportTicketRepository.save(supportTicket);
//    }	

    

}

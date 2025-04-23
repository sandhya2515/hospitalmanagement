
	package com.simple.patientapp.service;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.simple.patientapp.model.SupportTicket;
	import com.simple.patientapp.repository.SupportTicketRepository;

	import java.util.List;

	@Service
	public class PatientSupportTicketService {

	    @Autowired
	    private SupportTicketRepository ticketRepository;

	    // Method to create and save a new support ticket
	    public SupportTicket createTicket(SupportTicket ticket) {
	        return ticketRepository.save(ticket);
	    }

	    // Method to retrieve tickets for a specific patient by their puId
	    public List<SupportTicket> getTicketsByPatientPuId(String puId) {
	        return ticketRepository.findByPuId(puId);
	    }
	   
	}



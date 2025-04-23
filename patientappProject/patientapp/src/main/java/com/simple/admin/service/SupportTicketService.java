package com.simple.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.simple.patientapp.model.SupportTicket;


import com.simple.patientapp.repository.SupportTicketRepository;

@Service
public class SupportTicketService {
	
	@Autowired
	private SupportTicketRepository supportticketrepository;
	
	public List<SupportTicket> getalldoctorsupportticket() {
		
		return supportticketrepository.findByDoctorUidNotNullAndPuIdNull();
		
		
	}

	public List<SupportTicket> getallpatientsupportticket() {

		return supportticketrepository.findByPuIdNotNullAndDoctorUidNull();
	}

	public void SaveTicket(SupportTicket supportticket) {
		
		supportticketrepository.save(supportticket);
			
	}

	public SupportTicket getSupportTicketById(Long id) {
        // Using findById to fetch the support ticket from the database
        Optional<SupportTicket> optionalTicket = supportticketrepository.findById(id);
        // If found, return the ticket, otherwise return null or handle accordingly
        return optionalTicket.orElse(null);
    }


	
	
	
	

}

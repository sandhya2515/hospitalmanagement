
	package com.simple.patientapp.repository;


	import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simple.patientapp.model.SupportTicket;

	public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
	    // Custom query methods (if needed)
	
	
		@Query("SELECT st FROM SupportTicket st WHERE st.doctorUid IS NOT NULL AND st.puId IS NULL")
	    List<SupportTicket> findByDoctorUidNotNullAndPuIdNull();
		
		@Query("SELECT st FROM SupportTicket st WHERE st.puId IS NOT NULL AND st.doctorUid IS NULL")
	    List<SupportTicket> findByPuIdNotNullAndDoctorUidNull();
		
		List<SupportTicket> findByPuId(String puId);// Patient Support Ticket
	}




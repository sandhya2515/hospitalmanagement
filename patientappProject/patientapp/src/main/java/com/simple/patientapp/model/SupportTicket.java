
	package com.simple.patientapp.model;

	import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;

	@Entity
	public class SupportTicket {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String puId; // or patientId
	    private String doctorUid;
	    private String category;
	    private String subject;
	    private String description;
	    private String status; // e.g., "Open", "In Progress", "Closed"
	    @Column(name = "created_at")
	    private LocalDateTime createdAt;
	    
	    public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getpuId() {
			return puId;
		}
		public void setpuId(String puId) {
			this.puId = puId;
		}
	
		
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getDoctorUid() {
			return doctorUid;
		}
		public void setDoctorUid(String doctorUid) {
			this.doctorUid = doctorUid;
		}
	
		  public SupportTicket() {
		        this.status = "Pending";  // Default status when the ticket is created
		        this.createdAt = LocalDateTime.now();  // Automatically set creation time
		    }
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		  
	
	}
	
		
	    
	



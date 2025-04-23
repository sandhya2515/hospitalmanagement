package com.simple.patientapp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Appointments 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String puId;
	private String doctorUID;
	private String fullname;
	private String contact_number;
	private String appointment_date;
	private String appointment_time;
	private String department;
	private String symptoms;
	private String gender;
    private Integer age; // New field for age
    private String AppointmentId;
	
    
    @Column(name = "insertdate", updatable = false)
    private String insertdate;
    @Column(name = "updatedate")
    private String updatedate;
    private String status;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String STATUS_UPCOMING = "Upcoming";
    public static final String STATUS_Cancle =   "Cancelled";
    
    @Column(length = 5000)
    private String prescription; // To store prescription details

    @Column(length = 5000)
    private String diagnosis; // To store diagnosis deta

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPuId() {
		return puId;
	}
	public void setPuId(String puId) {
		this.puId = puId;
	}
	public String getAppointmentId() {
		return AppointmentId;
	}
	public void setAppointmentId(String AppointmentId) {
		this.AppointmentId = AppointmentId;
	}
	
	public String getDoctorUID() {
		return doctorUID;
	}
	public void setDoctorUID(String doctorUID) {
		this.doctorUID = doctorUID;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
	
    @PrePersist
    protected void onCreate() {
        this.insertdate = LocalDateTime.now().format(formatter);
        this.updatedate = LocalDateTime.now().format(formatter);
        this.status = STATUS_UPCOMING;

    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedate = LocalDateTime.now().format(formatter);
        
    }
 // Add a method to check if the appointment can be updated
    public boolean canBeUpdated() { 
        LocalDateTime appointmentDateTime = LocalDateTime.parse(this.appointment_date + "T" + this.appointment_time);
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(appointmentDateTime.minusHours(24)); // Allow update if it's more than 24 hours away
    }
	


}
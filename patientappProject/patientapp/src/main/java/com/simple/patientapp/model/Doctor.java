package com.simple.patientapp.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;


@Entity
public class Doctor {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long Id; // Changed to camelCase

	    private String doctorUID; // Changed to camelCase

	    private String password; // Changed to camelCase

	    private String title; // Changed to camelCase

	    private String firstName; // Changed to camelCase

	    private String middleName; // Changed to camelCase

	    private String lastName; // Changed to camelCase

	    private String gender; // Changed to camelCase

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date dateOfBirth; // Changed to camelCase

	    private String maritalStatus; // Changed to camelCase

	    @Lob
	    @Column(name = "profile_photo", columnDefinition = "LONGBLOB")
	    private byte[] profilePhoto; // Changed to camelCase

	    private String primaryPhoneNumber; // Changed to camelCase

	    private String secondaryPhoneNumber; // Changed to camelCase

	    private String primaryEmail; // Changed to camelCase

	    private String secondaryEmail; // Changed to camelCase

	    private String currentAddress; // Changed to camelCase

	    private String currentLandmark; // Changed to camelCase

	    private String currentPostalCode; // Changed to camelCase

	    private String currentCountry; // Changed to camelCase

	    private String currentState; // Changed to camelCase

	    private String currentCity; // Changed to camelCase

	    private String permanentAddress; // Changed to camelCase

	    private String permanentLandmark; // Changed to camelCase

	    private String permanentPostalCode; // Changed to camelCase

	    private String permanentCountry; // Changed to camelCase

	    private String permanentState; // Changed to camelCase

	    private String permanentCity; // Changed to camelCase

	    private String higherEducation; // Changed to camelCase

	    private String specialization; // Changed to camelCase

	    private String department; // Changed to camelCase

	    private String experience; // Changed to camelCase

	    @Lob
	    @Column(name = "certificates", columnDefinition = "LONGBLOB")
	    private byte[] certificates; // Changed to camelCase

	    // Getters and setters

	    

	    public String getDoctorUID() {
	        return doctorUID; // Changed to camelCase
	    }

	    public long getId() {
			return Id;
		}

		public void setId(long id) {
			Id = id;
		}

		public void setDoctorUID(String doctorUID) {
	        this.doctorUID = doctorUID; // Changed to camelCase
	    }

	    public String getPassword() {
	        return password; // Changed to camelCase
	    }

	    public void setPassword(String password) {
	        this.password = password; // Changed to camelCase
	    }

	    public String getTitle() {
	        return title; // Changed to camelCase
	    }

	    public void setTitle(String title) {
	        this.title = title; // Changed to camelCase
	    }

	    public String getFirstName() {
	        return firstName; // Changed to camelCase
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName; // Changed to camelCase
	    }

	    public String getMiddleName() {
	        return middleName; // Changed to camelCase
	    }

	    public void setMiddleName(String middleName) {
	        this.middleName = middleName; // Changed to camelCase
	    }

	    public String getLastName() {
	        return lastName; // Changed to camelCase
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName; // Changed to camelCase
	    }

	    public String getGender() {
	        return gender; // Changed to camelCase
	    }

	    public void setGender(String gender) {
	        this.gender = gender; // Changed to camelCase
	    }

	  

	    public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getMaritalStatus() {
	        return maritalStatus; // Changed to camelCase
	    }

	    public void setMaritalStatus(String maritalStatus) {
	        this.maritalStatus = maritalStatus; // Changed to camelCase
	    }

	    public byte[] getProfilePhoto() {
	        return profilePhoto; // Changed to camelCase
	    }

	    public void setProfilePhoto(byte[] profilePhoto) {
	        this.profilePhoto = profilePhoto; // Changed to camelCase
	    }

	    public String getPrimaryPhoneNumber() {
	        return primaryPhoneNumber; // Changed to camelCase
	    }

	    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
	        this.primaryPhoneNumber = primaryPhoneNumber; // Changed to camelCase
	    }

	    public String getSecondaryPhoneNumber() {
	        return secondaryPhoneNumber; // Changed to camelCase
	    }

	    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
	        this.secondaryPhoneNumber = secondaryPhoneNumber; // Changed to camelCase
	    }

	    public String getPrimaryEmail() {
	        return primaryEmail; // Changed to camelCase
	    }

	    public void setPrimaryEmail(String primaryEmail) {
	        this.primaryEmail = primaryEmail; // Changed to camelCase
	    }

	    public String getSecondaryEmail() {
	        return secondaryEmail; // Changed to camelCase
	    }

	    public void setSecondaryEmail(String secondaryEmail) {
	        this.secondaryEmail = secondaryEmail; // Changed to camelCase
	    }

	    public String getCurrentAddress() {
	        return currentAddress; // Changed to camelCase
	    }

	    public void setCurrentAddress(String currentAddress) {
	        this.currentAddress = currentAddress; // Changed to camelCase
	    }

	    public String getCurrentLandmark() {
	        return currentLandmark; // Changed to camelCase
	    }

	    public void setCurrentLandmark(String currentLandmark) {
	        this.currentLandmark = currentLandmark; // Changed to camelCase
	    }

	    public String getCurrentPostalCode() {
	        return currentPostalCode; // Changed to camelCase
	    }

	    public void setCurrentPostalCode(String currentPostalCode) {
	        this.currentPostalCode = currentPostalCode; // Changed to camelCase
	    }

	    public String getCurrentCountry() {
	        return currentCountry; // Changed to camelCase
	    }

	    public void setCurrentCountry(String currentCountry) {
	        this.currentCountry = currentCountry; // Changed to camelCase
	    }

	    public String getCurrentState() {
	        return currentState; // Changed to camelCase
	    }

	    public void setCurrentState(String currentState) {
	        this.currentState = currentState; // Changed to camelCase
	    }

	    public String getCurrentCity() {
	        return currentCity; // Changed to camelCase
	    }

	    public void setCurrentCity(String currentCity) {
	        this.currentCity = currentCity; // Changed to camelCase
	    }

	    public String getPermanentAddress() {
	        return permanentAddress; // Changed to camelCase
	    }

	    public void setPermanentAddress(String permanentAddress) {
	        this.permanentAddress = permanentAddress; // Changed to camelCase
	    }

	    public String getPermanentLandmark() {
	        return permanentLandmark; // Changed to camelCase
	    }

	    public void setPermanentLandmark(String permanentLandmark) {
	        this.permanentLandmark = permanentLandmark; // Changed to camelCase
	    }

	    public String getPermanentPostalCode() {
	        return permanentPostalCode; // Changed to camelCase
	    }

	    public void setPermanentPostalCode(String permanentPostalCode) {
	        this.permanentPostalCode = permanentPostalCode; // Changed to camelCase
	    }

	    public String getPermanentCountry() {
	        return permanentCountry; // Changed to camelCase
	    }

	    public void setPermanentCountry(String permanentCountry) {
	        this.permanentCountry = permanentCountry; // Changed to camelCase
	    }

	    public String getPermanentState() {
	        return permanentState; // Changed to camelCase
	    }

	    public void setPermanentState(String permanentState) {
	        this.permanentState = permanentState; // Changed to camelCase
	    }

	    public String getPermanentCity() {
	        return permanentCity; // Changed to camelCase
	    }

	    public void setPermanentCity(String permanentCity) {
	        this.permanentCity = permanentCity; // Changed to camelCase
	    }

	    public String getHigherEducation() {
	        return higherEducation; // Changed to camelCase
	    }

	    public void setHigherEducation(String higherEducation) {
	        this.higherEducation = higherEducation; // Changed to camelCase
	    }

	    public String getSpecialization() {
	        return specialization; // Changed to camelCase
	    }

	    public void setSpecialization(String specialization) {
	        this.specialization = specialization; // Changed to camelCase
	    }

	    public String getDepartment() {
	        return department; // Changed to camelCase
	    }

	    public void setDepartment(String department) {
	        this.department = department; // Changed to camelCase
	    }

	    public String getExperience() {
	        return experience; // Changed to camelCase
	    }

	    public void setExperience(String experience) {
	        this.experience = experience; // Changed to camelCase
	    }

	    public byte[] getCertificates() {
	        return certificates; // Changed to camelCase
	    }

	    public void setCertificates(byte[] certificates) {
	        this.certificates = certificates; // Changed to camelCase
	    }
	
	
}
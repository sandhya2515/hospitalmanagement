package com.simple.admin.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.repository.DoctorRepository;
import com.simple.admin.service.DoctorService;

@Controller
public class DoctorController {
	
	@Autowired
	private DoctorService doctorservice;
	
	@GetMapping("/doctorform")
    public String showRegistrationForm(Model model) {
    	model.addAttribute("doctor", new Doctor());

        return "/Admin/doctorform";    
    }
    
    @PostMapping("/doctor/register")
    public String registerDoctor(
        @RequestParam("title") String title,
        @RequestParam("firstName") String firstName, 
        @RequestParam("middleName") String middleName,
        @RequestParam("lastName") String lastName,
        @RequestParam("gender") String gender,
        @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
        @RequestParam("maritalStatus") String maritalStatus,
        @RequestParam("profilePhoto") MultipartFile profilePhoto,
        @RequestParam("primaryPhoneNumber") String primaryPhoneNumber,
        @RequestParam("secondaryPhoneNumber") String secondaryPhoneNumber,
        @RequestParam("primaryEmail") String primaryEmail,
        @RequestParam("secondaryEmail") String secondaryEmail,
        @RequestParam("currentAddress") String currentAddress,
        @RequestParam("currentLandmark") String currentLandmark,
        @RequestParam("currentPostalCode") String currentPostalCode,
        @RequestParam("currentCountry") String currentCountry,
        @RequestParam("currentState") String currentState,
        @RequestParam("currentCity") String currentCity,
        @RequestParam("permanentAddress") String permanentAddress,
        @RequestParam("permanentLandmark") String permanentLandmark,
        @RequestParam("permanentPostalCode") String permanentPostalCode,
        @RequestParam("permanentCountry") String permanentCountry,
        @RequestParam("permanentState") String permanentState,
        @RequestParam("permanentCity") String permanentCity,
        @RequestParam("higherEducation") String higherEducation,
        @RequestParam("specialization") String specialization,
        @RequestParam("department") String department,
        @RequestParam("experience") String experience,
        @RequestParam("certificates") MultipartFile certificates
    ) throws IOException {

        Doctor doctor = new Doctor();
        doctor.setTitle(title);
        doctor.setFirstName(firstName);
        doctor.setMiddleName(middleName);
        doctor.setLastName(lastName);
        doctor.setGender(gender);
        doctor.setDateOfBirth(dateOfBirth);
        doctor.setMaritalStatus(maritalStatus);
        doctor.setPrimaryPhoneNumber(primaryPhoneNumber);
        doctor.setSecondaryPhoneNumber(secondaryPhoneNumber);
        doctor.setPrimaryEmail(primaryEmail);
        doctor.setSecondaryEmail(secondaryEmail);
        doctor.setCurrentAddress(currentAddress);
        doctor.setCurrentLandmark(currentLandmark);
        doctor.setCurrentPostalCode(currentPostalCode);
        doctor.setCurrentCountry(currentCountry);
        doctor.setCurrentState(currentState);
        doctor.setCurrentCity(currentCity);
        doctor.setPermanentAddress(permanentAddress);
        doctor.setPermanentLandmark(permanentLandmark);
        doctor.setPermanentPostalCode(permanentPostalCode);
        doctor.setPermanentCountry(permanentCountry);
        doctor.setPermanentState(permanentState);
        doctor.setPermanentCity(permanentCity);
        doctor.setHigherEducation(higherEducation);
        doctor.setSpecialization(specialization);
        doctor.setDepartment(department);
        doctor.setExperience(experience);

        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            doctor.setProfilePhoto(profilePhoto.getBytes());
        }
        
        if (certificates != null && !certificates.isEmpty()) {
            doctor.setCertificates(certificates.getBytes());
        }

        doctorservice.SaveDoctor(doctor);

        return "redirect:/managedoctor";
    }
    
    
      @Autowired
      private DoctorRepository doctorrepository;
    
      @GetMapping("/managedoctor/{Id}")
      public ResponseEntity<byte[]> getdoctorImage(@PathVariable long Id){
    	
    	Doctor doctor = doctorrepository.findById(Id).orElse(null);
    	
    	if (doctor == null || doctor.getProfilePhoto() == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.IMAGE_JPEG);   	
		return new ResponseEntity<>(doctor.getProfilePhoto(), headers, HttpStatus.OK);
   
    }
    
    
        @GetMapping("/editdoctor/{Id}")
	    public String showEditForm(@PathVariable("Id") Long Id, Model model) {
	    Doctor doctor = doctorservice.getdoctorbyId(Id);
	    model.addAttribute("doctor", doctor);
	    return "/Admin/updatedoctor";
	}
        
        
        @PostMapping("/updatedoctor/{Id}")
        public String updateDoctor(@PathVariable("Id") Long Id,
                                   @RequestParam("title") String title,
                                   @RequestParam("firstName") String firstName, 
                                   @RequestParam("middleName") String middleName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("gender") String gender,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
                                   @RequestParam("maritalStatus") String maritalStatus,
                                   @RequestParam("profilePhoto") MultipartFile profilePhoto,
                                   @RequestParam("primaryPhoneNumber") String primaryPhoneNumber,
                                   @RequestParam("secondaryPhoneNumber") String secondaryPhoneNumber,
                                   @RequestParam("primaryEmail") String primaryEmail,
                                   @RequestParam("secondaryEmail") String secondaryEmail,
                                   @RequestParam("currentAddress") String currentAddress,
                                  // @RequestParam("currentLandmark") String currentLandmark,
                                  @RequestParam(value = "currentLandmark", required = false) String currentLandmark,

                                   @RequestParam("currentPostalCode") String currentPostalCode,
                                   @RequestParam("currentCountry") String currentCountry,
                                   @RequestParam("currentState") String currentState,
                                   @RequestParam("currentCity") String currentCity,
                                   @RequestParam("permanentAddress") String permanentAddress,
                                   @RequestParam(value = "permanentLandmark", required = false) String permanentLandmark,

                                   @RequestParam("permanentPostalCode") String permanentPostalCode,
                                   @RequestParam("permanentCountry") String permanentCountry,
                                   @RequestParam("permanentState") String permanentState,
                                   @RequestParam("permanentCity") String permanentCity,
                                   @RequestParam("higherEducation") String higherEducation,
                                   @RequestParam("specialization") String specialization,
                                   @RequestParam("department") String department,
                                   @RequestParam("experience") String experience,
                                   @RequestParam("certificates") MultipartFile certificates) throws IOException {

            Doctor existingDoctor = doctorservice.getdoctorbyId(Id);

            if (existingDoctor != null) {
                // Update doctor details
                existingDoctor.setTitle(title);
                existingDoctor.setFirstName(firstName);
                existingDoctor.setMiddleName(middleName);
                existingDoctor.setLastName(lastName);
                existingDoctor.setGender(gender);
                existingDoctor.setDateOfBirth(dateOfBirth);
                existingDoctor.setMaritalStatus(maritalStatus);
                existingDoctor.setPrimaryPhoneNumber(primaryPhoneNumber);
                existingDoctor.setSecondaryPhoneNumber(secondaryPhoneNumber);
                existingDoctor.setPrimaryEmail(primaryEmail);
                existingDoctor.setSecondaryEmail(secondaryEmail);
                existingDoctor.setCurrentAddress(currentAddress);
                existingDoctor.setCurrentLandmark(currentLandmark);
                existingDoctor.setCurrentPostalCode(currentPostalCode);
                existingDoctor.setCurrentCountry(currentCountry);
                existingDoctor.setCurrentState(currentState);
                existingDoctor.setCurrentCity(currentCity);
                existingDoctor.setPermanentAddress(permanentAddress);
                existingDoctor.setPermanentLandmark(permanentLandmark);
                existingDoctor.setPermanentPostalCode(permanentPostalCode);
                existingDoctor.setPermanentCountry(permanentCountry);
                existingDoctor.setPermanentState(permanentState);
                existingDoctor.setPermanentCity(permanentCity);
                existingDoctor.setHigherEducation(higherEducation);
                existingDoctor.setSpecialization(specialization);
                existingDoctor.setDepartment(department);
                existingDoctor.setExperience(experience);

                // Handle file uploads
                if (profilePhoto != null && !profilePhoto.isEmpty()) {
                    existingDoctor.setProfilePhoto(profilePhoto.getBytes());
                }
                if (certificates != null && !certificates.isEmpty()) {
                    existingDoctor.setCertificates(certificates.getBytes());
                }

                doctorservice.SavenewDoctor(existingDoctor);
            }

            return "redirect:/managedoctor"; // Use redirect to prevent form resubmission
        } 
    
        
        @GetMapping("/deletedoctor/{Id}")
        public String DeleteDoctor(@PathVariable("Id") Long Id, Model model) {
            Doctor doctor = doctorservice.deleteDoctorById(Id);
            model.addAttribute("doctor", doctor);
            return "redirect:/managedoctor";
            
        }
        
        
        
        @GetMapping("/doctor/certificate/{Id}")
        public ResponseEntity<byte[]> viewDoctorCertificate(@PathVariable long Id) {
            Doctor doctor = doctorrepository.findById(Id).orElse(null);

            if (doctor == null || doctor.getCertificates() == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Set headers for viewing the file inline in the browser
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);  // Assuming the certificate is a PDF
            headers.setContentDisposition(ContentDisposition.inline().filename("certificate.pdf,jpg,png").build());  // Display in browser

            return new ResponseEntity<>(doctor.getCertificates(), headers, HttpStatus.OK);
        }

        
        @GetMapping("/viewdoctor/{Id}")
        public String viewDoctorDetails(@PathVariable("Id") Long Id, Model model) {
            Doctor doctor = doctorservice.getdoctorbyId(Id);
            model.addAttribute("doctor", doctor);
            return "/Admin/viewdoctordetails";
        }
        
        
        @GetMapping("/updatedoctor/{Id}")
        public String updatedoctorDetails(@PathVariable("Id") Long Id, Model model) {
            Doctor doctor = doctorservice.getdoctorbyId(Id);
            model.addAttribute("doctor", doctor);
            return "/Admin/updatedoctor";
        }
        

}

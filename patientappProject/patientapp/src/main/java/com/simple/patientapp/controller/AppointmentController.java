package com.simple.patientapp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.patientapp.model.Appointments;
import com.simple.patientapp.model.Availability;
import com.simple.patientapp.model.Department;
import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.repository.AvailabilityRepository;
import com.simple.patientapp.service.AppointmentServices;
import com.simple.patientapp.service.PatientDepartmentService;
import com.simple.patientapp.service.PatientDoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AppointmentController {

    @Autowired
    private AppointmentServices appointmentServices;
    @Autowired
    private PatientDepartmentService departmentService;
    @Autowired
    private PatientDoctorService doctorService;
    @Autowired
    private AvailabilityRepository availabilityRepository;
    
    
    public boolean isDoctorAvailable(String doctorUid, LocalDate appointmentDate, LocalTime appointmentTime) {
        List<Availability> availabilities = availabilityRepository.findByDoctorUid(doctorUid);
        
        for (Availability availability : availabilities) {
            if (availability.getAvailableDate().equals(appointmentDate) && 
                !appointmentTime.isBefore(availability.getStartTime()) && 
                !appointmentTime.isAfter(availability.getEndTime())) {
                return true;
            }
        }
        return false;
    }


    @GetMapping("/bookappointment")
    public String bookAppointment(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        List<Doctor> doctors = doctorService.getAllFirstname();
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);
        return "/Patient/bookappointment";
    }
     

    @PostMapping("/bookappointment")
    public String bookappointment(@ModelAttribute Appointments info, Model model, HttpSession session) {
        String patientPuId = (String) session.getAttribute("PaUid");

        if (patientPuId != null) {
            int appointmentCount = appointmentServices.countAppointmentsByPuId(patientPuId);

            if (appointmentCount >= 3) {
                model.addAttribute("message", "Free Subscription is ended.Please Book Through Paid Subscription.");
                return "redirect:/buypackage";  
            }

            info.setPuId(patientPuId);
            model.addAttribute("message", "Appointment booked successfully!");

            appointmentServices.saveAppointment(info);
            
            return "redirect:/"; 
       
        } else {
            model.addAttribute("errorMessage", "Error: No logged-in patient found.");
            return "/Patient/bookappointment"; 
        }
    }


    @GetMapping("/viewappointment")
    public String viewAppointment(Model model, HttpSession session) {
    	List<Department> departments = departmentService.getAllDepartments();
        List<Doctor> doctors = doctorService.getAllFirstname();
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);
    	String patientPuId = (String) session.getAttribute("PaUid");

        if (patientPuId != null) {
            List<Appointments> appointments = appointmentServices.getAppointmentsByPuId(patientPuId);
            model.addAttribute("appointments", appointments);
        } else {
            model.addAttribute("errorMessage", "No logged-in patient found.");
        }

        return "/Patient/viewappointment";
    }
    @GetMapping("/viewappointment/{id}")
    @ResponseBody
    public ResponseEntity<Appointments> getAppointmentDetails(@PathVariable("id") int id) {
        Appointments appointment = appointmentServices.findAppointmentById(id);

        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
    @PostMapping("/cancelviewappointment/{id}")
    @ResponseBody
    public ResponseEntity<String> cancelAppointment(@PathVariable("id") int id) {
        Appointments appointment = appointmentServices.findAppointmentById(id);

        if (appointment != null && appointment.getStatus().equals(Appointments.STATUS_UPCOMING)) {
            appointmentServices.cancelAppointment(id);
            return ResponseEntity.ok("Appointment cancelled successfully.");
        } else if (appointment != null) {
            return ResponseEntity.badRequest().body("Cannot cancel a non-upcoming appointment.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/viewhistory")
    public String view(Model model, HttpSession session) {
    	List<Department> departments = departmentService.getAllDepartments();
        List<Doctor> doctors = doctorService.getAllFirstname();
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);
    	String patientPuId = (String) session.getAttribute("PaUid");

        if (patientPuId != null) {
            List<Appointments> appointments = appointmentServices.getAppointmentsByPuId(patientPuId);
            model.addAttribute("appointments", appointments);
        } else {
            model.addAttribute("errorMessage", "No logged-in patient found.");
        }	return "/Patient/viewhistory";
    }
    
    @GetMapping("/doctor-availability/{doctorUid}")
    public ResponseEntity<List<Availability>> getDoctorAvailability(@PathVariable("doctorUid") String doctorUid) {
        List<Availability> availabilityList = availabilityRepository.findByDoctorUid(doctorUid);
        return ResponseEntity.ok(availabilityList);
    }
    
    @GetMapping("/doctor-availability/{doctorUid}/{appointmentDate}")
    public ResponseEntity<List<Availability>> getDoctorAvailabilityByDate(
            @PathVariable("doctorUid") String doctorUid,
            @PathVariable("appointmentDate") String appointmentDateStr) {
        LocalDate appointmentDate = LocalDate.parse(appointmentDateStr);

        List<Availability> availabilityList = availabilityRepository.findByDoctorUidAndAvailableDate(doctorUid, appointmentDate);

        return ResponseEntity.ok(availabilityList);
    }

}

package com.simple.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.simple.patientapp.model.SupportTicket;

import com.simple.patientapp.model.Department;
import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.model.Package;
import com.simple.patientapp.model.Patient;
import com.simple.admin.service.DepartmentService;
import com.simple.admin.service.DoctorService;
import com.simple.admin.service.SupportTicketService;
import com.simple.admin.service.PackageService;
import com.simple.admin.service.AdminPatientService;

@Controller
public class DashboardController {
	
	     
	     @Autowired
	     private DepartmentService departmentservice;
	     
	     @GetMapping("/managedepartment")
	     public String getDepartments(Model model) {
	         List<Department> departments = departmentservice.getAllDepartments();
	         model.addAttribute("departments", departments);
	         return "/Admin/managedepartment"; // Replace with the name of your HTML/Thymeleaf template
	     }
	     
	     
	     @Autowired
	     private DoctorService doctorservice;
	     
	     @GetMapping("/managedoctor")
	     public String getDoctors(Model model) {
	     	List<Doctor> doctors = doctorservice.getAllDoctors();
	         model.addAttribute("doctors", doctors);
	         return "/Admin/managedoctor";  // Make sure this view name matches the actual Thymeleaf template name
	     }
	     
	     
	    @Autowired
	 	private PackageService packageservice;
	     
	     @GetMapping("/managepackage")
	 	public String getPackages(Model model) {
	 		List<Package> packaages = packageservice.getAllPackages();
	 		model.addAttribute("packaages", packaages);
	 		return "/Admin/managepackage";	
	 	}
	     
	     
	    @Autowired
	 	private AdminPatientService patientservice;
	     
	     @GetMapping("/managepatient")
	     public String getPatients(Model model) {
	     	List<Patient> patients = patientservice.getAllPatient();
	     	model.addAttribute("patients", patients);
	 		return "/Admin/managepatient";
	     	
	     	
	     }
	
	     
	     @Autowired
	     private SupportTicketService doctorsupportticketservice;
	     
	     @GetMapping("/managedoctorsupportticket")
	     public String getDoctorSupportTicket(Model model) {
	     	List<SupportTicket> supporttickets = doctorsupportticketservice.getalldoctorsupportticket();
	     	model.addAttribute("supporttickets", supporttickets);
	 		return "/Admin/doctorsupportticket"; 	
	     }
	     
	     @GetMapping("/managepatientsupportticket")
	     public String getPatientSupportTicket(Model model) {
	     	List<SupportTicket> supporttickets = doctorsupportticketservice.getallpatientsupportticket();
	     	model.addAttribute("supporttickets", supporttickets);
	 		return "/Admin/patientsupportticket";
	     	
	     	
	     }
	

}

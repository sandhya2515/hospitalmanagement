package com.simple.patientapp.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.patientapp.model.Department;
import com.simple.patientapp.model.Doctor;
import com.simple.patientapp.repository.DepartmentRepository;
import com.simple.patientapp.service.PatientDepartmentService;
import com.simple.patientapp.service.PatientDoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/departments")
public class PatientDepartmentController {

    // Mapping for the home page
	@Autowired
    private DepartmentRepository departmentRepository;
	@Autowired
    private PatientDepartmentService departmentService;
    @Autowired
    private PatientDoctorService doctorService;
    // Method to handle department page requests
     @GetMapping("/{departmentName}")
     public String getDepartmentPage(@PathVariable("departmentName") String departmentName, Model model) {
        // Fetch department data by name
       //  Department department = departmentRepository.findByName(departmentName);
     	Optional<Department> department = departmentRepository.findByDeptName(departmentName);

         List<Department> departments = departmentService.getAllDepartments();
         List<Doctor> doctors = doctorService.getAllFirstname();


        if (department != null) {
            // Add department details to the model
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("doctors", doctors);

            return "/Patient/department-page"; 
        
        } else {
            return "error"; 
        }
    }
     
     @GetMapping("/checkSession")
     @ResponseBody
     public Map<String, Boolean> checkSession(HttpSession session) {
         Map<String, Boolean> response = new HashMap<>();
         response.put("loggedIn", session.getAttribute("loggedInUser") != null);
         return response;
     }
}
 
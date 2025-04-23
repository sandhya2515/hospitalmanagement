package com.simple.admin.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.simple.patientapp.model.Department;
import com.simple.patientapp.repository.DepartmentRepository;
import com.simple.admin.service.DepartmentAlreadyExistsException;
import com.simple.admin.service.DepartmentService;

@Controller
public class DepartmentController {
	
	
	@Autowired
	private DepartmentService departmentservice;
	
	@GetMapping("/departmentform")
    public String DepartmentRegistration(Model model) {
        model.addAttribute("department", new Department());
        return "/Admin/departmentform";
    }

    @PostMapping("/department/register")
    public String RegisterDepartment(@RequestParam("deptImage") MultipartFile deptImage,
                                     @RequestParam("deptName") String deptName,
                                     @RequestParam("deptDesc") String deptDesc,
                                     Model model) throws IOException {
        // Create the Department object manually
        Department department = new Department();
        department.setDeptName(deptName);
        department.setDeptDesc(deptDesc);

        // Convert MultipartFile to byte[] and set it
        if (deptImage != null && !deptImage.isEmpty()) {
            department.setDeptImage(deptImage.getBytes());
        }
        
        try {
            departmentservice.SaveDepartment(department);
            return "redirect:/managedepartment"; // Redirect to the department list page if successful
        } catch (DepartmentAlreadyExistsException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "/Admin/departmentForm"; // Return to the form with an error message
        }

    }
    
    
    @Autowired
    private DepartmentRepository departmentrepository;
    
    @GetMapping("/managedepartment/{Id}")
    public ResponseEntity<byte[]> getdepartmentImage(@PathVariable long Id) {
        Department department = departmentrepository.findById(Id).orElse(null);

        if (department == null || department.getDeptImage() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(department.getDeptImage(), headers, HttpStatus.OK);
    }
    
    
    
    @GetMapping("/editdepartment/{Id}")
    public String showEditForm(@PathVariable("Id") Long Id, Model model) {
        Department department = departmentservice.getDepartmentById(Id);
        model.addAttribute("department", department);
        return "/Admin/updatedepartment";
    }

    
    @PostMapping("/updatedepartment/{Id}")	
    public String updateDepartment(@PathVariable("Id") Long Id,
    		                       @RequestParam("deptName") String deptName,
    		                       @RequestParam("deptDesc") String deptDesc,
                                   @RequestParam("deptImage") MultipartFile deptImage) throws IOException {
    	
    	
        Department existingDepartment = departmentservice.getDepartmentById(Id);
        if (existingDepartment != null) {
            existingDepartment.setDeptName(deptName);
            existingDepartment.setDeptDesc(deptDesc);

            if (deptImage != null && !deptImage.isEmpty()) {
                existingDepartment.setDeptImage(deptImage.getBytes());
            }

            departmentservice.SavenewDepartment(existingDepartment);
        }
        return "redirect:/managedepartment";
    }
    
    
    @GetMapping("/deletedepartment/{Id}")
    public String DeleteDepartment(@PathVariable("Id") Long Id, Model model) {
        String department = departmentservice.deleteDepartmentById(Id);
        model.addAttribute("department", department);
        return "redirect:/managedepartment";
    }
	

}

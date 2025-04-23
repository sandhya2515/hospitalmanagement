package com.simple.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simple.admin.service.AdminService;
import com.simple.patientapp.model.AdminLogin;


@Controller
public class AdminController {
	
	
	
	@Autowired
	private AdminService adminservice;
	
	@GetMapping("/adminlogin")
	public String ShowLogin(Model model) {
		 model.addAttribute("login", new AdminController());
		return "/Admin/login";
		}

    @PostMapping("/Dashboard")
    public String loginPatient(@ModelAttribute("login") AdminLogin admin, Model model) {
        boolean isAuthenticated = adminservice.authenticateAdmin(admin.getUsername(), admin.getPassword());
        
        if (isAuthenticated) {
            return "/Admin/admindashboard"; 
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "/Admin/login"; 
        }
    }

}

package com.simple.doctor.controller;


import com.simple.patientapp.model.Doctor;
import com.simple.doctor.service.DoctorLoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/doctor")
public class DoctorLoginController {

    @Autowired
    private DoctorLoginService loginServices;
    
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "/Doctor/doctorlogin"; // This should match your login.html template
    }

    @PostMapping("/login")
    public String loginDoctor(@ModelAttribute("doctor") Doctor doctor, RedirectAttributes redirectAttributes, HttpSession session) {
        boolean isAuthenticated = loginServices.authenticateDoctor(doctor.getPrimaryEmail(), doctor.getPassword());

        if (isAuthenticated) {
            // Fetch the full Doctor object
            Doctor loggedInDoctor = loginServices.findByPrimaryEmail(doctor.getPrimaryEmail());

            // Store user details in session
            session.setAttribute("loggedInUser", loggedInDoctor.getPrimaryEmail());
            session.setAttribute("doctorUID", loggedInDoctor.getDoctorUID());

            return "/Doctor/doctordashboard"; // Redirect to the home page or dashboard
        } else {
            redirectAttributes.addFlashAttribute("loginError", "Invalid username or password");
            return "/Doctor/doctorlogin"; // Redirect back to the login page
        }
    }
	
}


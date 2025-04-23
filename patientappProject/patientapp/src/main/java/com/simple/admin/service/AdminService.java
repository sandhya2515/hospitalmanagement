package com.simple.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.AdminLogin;
import com.simple.patientapp.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminrepository;
	
	
	public AdminLogin findByUsername( String username) {
		return adminrepository.findByUsername(username);
	}


	public boolean authenticateAdmin(String username, String password) {
		AdminLogin admin  = adminrepository.findByUsername(username);
	    if (admin != null && admin.getPassword().equals(password)) {
	        return true;
	    }
		// TODO Auto-generated method stub
		return false;
	}

}

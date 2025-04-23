package com.simple.patientapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple.patientapp.model.AdminLogin;

public interface AdminRepository extends JpaRepository<AdminLogin, Long>{

	AdminLogin findByUsername(String username);

}

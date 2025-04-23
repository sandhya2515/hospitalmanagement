package com.simple.patientapp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simple.patientapp.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> 
{
	

	   // Department findByName(String deptName);
		Optional<Department> findByDeptName(String deptName);

	}



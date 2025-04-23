package com.simple.patientapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Department;
import com.simple.patientapp.repository.DepartmentRepository;

import java.util.List;

@Service
public class PatientDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
//get list of Department
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
}


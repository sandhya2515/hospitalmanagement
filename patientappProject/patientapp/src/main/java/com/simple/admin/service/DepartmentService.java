package com.simple.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Department;
import com.simple.patientapp.repository.DepartmentRepository;



@Service
public class DepartmentService {
	
	@Autowired
    private DepartmentRepository departmentrepository;

    public void SaveDepartment(Department department) {
    	
    	if (department.getDeptId()== null || department.getDeptId().isEmpty()) {
    		String deptId = GenerateDepartmentId();
    		department.setDeptId(deptId);
    	}
    	
    	Optional<Department> existingDepartment = departmentrepository.findByDeptName(department.getDeptName());

        if (existingDepartment.isPresent()) {
            // If the department already exists, return an error message
            throw new DepartmentAlreadyExistsException("Department already exists");
        }
        System.out.println("saving department" + department);
        departmentrepository.save(department);
    }
    

    private String GenerateDepartmentId() {
		int length = 6;
		String characters = "1234567890";
		Random random = new Random();
		StringBuilder deptId = new StringBuilder(length);
		
		for (int i=0 ; i < length ; i++) {
			
			deptId.append(characters.charAt(random.nextInt(characters.length())));
		}
		
		
		return "DEPT" + deptId.toString();
	}

	public List<Department> getAllDepartments() {
        return departmentrepository.findAll();
    }

    
    public Department getDepartmentById(Long Id) {
        return departmentrepository.findById(Id).orElse(null);
    }

    public Department UpdateDepartment(Department department) {
        return departmentrepository.save(department);
    }

	public String deleteDepartmentById(Long Id) {	
		departmentrepository.deleteById(Id);
		return null;
	}


	public void SavenewDepartment(Department existingDepartment) {
		
		departmentrepository.save(existingDepartment);
		// TODO Auto-generated method stub
		
	}

}

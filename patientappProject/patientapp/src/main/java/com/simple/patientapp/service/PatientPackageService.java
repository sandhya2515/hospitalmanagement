package com.simple.patientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Package;
import com.simple.patientapp.repository.PackageRepository;

@Service
public class PatientPackageService {
	
	@Autowired
	private PackageRepository packageRepository;
	
	 public List<Package> getAllPackages() {
	        return packageRepository.findAll();

}
}
package com.simple.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.patientapp.model.Package;

import com.simple.patientapp.repository.PackageRepository;

@Service
public class PackageService {
	
	@Autowired
	private PackageRepository packagerepository;
	
	public void SavePackage(Package pacakge) {
		
		if(pacakge.getPackageId() == null || pacakge.getPackageId().isEmpty()) {
			
			String packageId = GerneratePackageId();
			pacakge.setPackageId(packageId);
		}
		
		Optional<Package> existingPackage = packagerepository.findByPackageName(pacakge.getPackageName());

        if (existingPackage.isPresent()) {
            // If the department already exists, return an error message
            throw new PackageAlreadyExistsException("Package already exists");
        }
		System.out.println("package saved " + pacakge);
		packagerepository.save(pacakge);	
	}
	
	private String GerneratePackageId() {
		
		int length = 6;
		String characters = "1234567890";
		Random random = new Random();
		StringBuilder packageId = new StringBuilder(length);
		
		for (int i=0 ; i < length ; i++) {
			
			packageId.append(characters.charAt(random.nextInt(characters.length())));
		}
		
		
		return "PACK" + packageId.toString();

	}

	public List<Package> getAllPackages(){
		return packagerepository.findAll();
		
	}
	
	public Package getPakageById (Long Id) {
		return packagerepository.findById(Id).orElse(null);	
	}
	
	public Package UpdatePackage(Package subscription) {
		return packagerepository.save(subscription);	
	}
	
	public String deletePackageById(Long Id) {
		packagerepository.deleteById(Id);
		return null;
		
	}

	public void SavenewPackage(Package existingpackaage) {
		
		packagerepository.save(existingpackaage);
		// TODO Auto-generated method stub
		
	}

}

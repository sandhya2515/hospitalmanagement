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

import com.simple.admin.service.PackageAlreadyExistsException;
import com.simple.admin.service.PackageService;
import com.simple.patientapp.model.Package;
import com.simple.patientapp.repository.PackageRepository;


@Controller
public class PackageController {
	
	
	@Autowired
	private PackageService packageservice;
	
	@GetMapping("/packageform")
	public String ShowPackageForm(Model model) {
		model.addAttribute("package", new Package());
		return "/Admin/packageform";
		}
	
	
	@PostMapping("/Package/register")
	public String RegisterPackage(@RequestParam("packageName") String packageName,
			                      @RequestParam("packageDesc") String packageDesc,
			                      @RequestParam("packagePrice") Long packagePrice,
			                      @RequestParam("packageImage") MultipartFile packageImage, Model model) throws IOException {
		
		
		
		Package packaage = new Package();
		packaage.setPackageName(packageName);
		packaage.setPackageDesc(packageDesc);
		packaage.setPackagePrice(packagePrice);
		
		
		if (packageImage != null && !packageImage.isEmpty()) {
			packaage.setPackageImage(packageImage.getBytes());
        }
		
		try {
			packageservice.SavePackage(packaage);
			return "redirect:/managepackage";
		}catch(PackageAlreadyExistsException ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "/Admin/packageForm";
						
		}
				
	}
	
	
	@Autowired
    private PackageRepository packagerepository;
	
	
	@GetMapping("/managepackage/{Id}")
    public ResponseEntity<byte[]> getpackageImage(@PathVariable long Id){
    	
    	Package packaage = packagerepository.findById(Id).orElse(null);
    	
    	if (packaage == null || packaage.getPackageImage() == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.IMAGE_JPEG);   	
		return new ResponseEntity<>(packaage.getPackageImage(), headers, HttpStatus.OK);
    	
    	
    }
    
    @GetMapping("/editpackage/{Id}")
    public String showEditForm(@PathVariable("Id") Long Id, Model model) {
        Package packaage = packageservice.getPakageById(Id);
        model.addAttribute("packaage", packaage);
        return "/Admin/updatepackageform";
    }

    
    @PostMapping("/updatepackage/{Id}")	
    public String updateDepartment(@PathVariable("Id") Long Id,
    		                       @RequestParam("packageName") String packageName,
    		                       @RequestParam("packageDesc") String packageDesc,
    		                       @RequestParam("packagePrice") Long packagePrice,
                                   @RequestParam("packageImage") MultipartFile packageImage) throws IOException {
    	
    	
        Package existingpackaage = packageservice.getPakageById(Id);
        if (existingpackaage != null) {
        	existingpackaage.setPackageName(packageName);
        	existingpackaage.setPackageDesc(packageDesc);
        	existingpackaage.setPackagePrice(packagePrice);

            if (packageImage != null && !packageImage.isEmpty()) {
            	existingpackaage.setPackageImage(packageImage.getBytes());
            }

            packageservice.SavenewPackage(existingpackaage);
        }
        return "redirect:/managepackage";
    }
    
    @GetMapping("/deletepackage/{Id}")
    public String DeleteDepartment(@PathVariable("Id") Long Id, Model model) {
        String packaage = packageservice.deletePackageById(Id);
        model.addAttribute("Package", packaage);
        return "redirect:/managepackage";
    }


	
	

}

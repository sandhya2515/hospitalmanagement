package com.simple.patientapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple.patientapp.model.Package;

public interface PackageRepository extends JpaRepository<Package,Long>
{
	//Package findByPackageName(String packageName);
	Optional<Package> findByPackageName(String packageName);


}

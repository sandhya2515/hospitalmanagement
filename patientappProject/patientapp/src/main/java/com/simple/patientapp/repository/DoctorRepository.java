package com.simple.patientapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.simple.patientapp.model.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> 
{

	Doctor findByFirstName(String firstName);

    Doctor findByPrimaryEmail(String primaryEmail);
}
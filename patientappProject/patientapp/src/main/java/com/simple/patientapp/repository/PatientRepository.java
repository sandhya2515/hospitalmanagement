package com.simple.patientapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.simple.patientapp.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    Patient findByContact(String contact);
    Patient findByPuId(String puId); // This method should now work correctly
}

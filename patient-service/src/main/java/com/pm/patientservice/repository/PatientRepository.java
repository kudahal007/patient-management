package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, String> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, String id);
}

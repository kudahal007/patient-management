package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
<<<<<<< HEAD
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

=======
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
>>>>>>> e036e29 (initial commit for patient-service)
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, String> {
    boolean existsByEmail(String email);
<<<<<<< HEAD

=======
>>>>>>> e036e29 (initial commit for patient-service)
    boolean existsByEmailAndIdNot(String email, String id);
}

package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
<<<<<<< HEAD
import com.pm.patientservice.exception.PatientNotFoundException;
=======
import com.pm.patientservice.exception.InvalidAgeException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
>>>>>>> e036e29 (initial commit for patient-service)
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.List;
import java.util.UUID;
=======
import java.time.Period;
import java.util.List;
>>>>>>> e036e29 (initial commit for patient-service)

@Service
public class PatientService {
    private final PatientRepository patientRepository;
<<<<<<< HEAD

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
=======
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
>>>>>>> e036e29 (initial commit for patient-service)
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

<<<<<<< HEAD
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + patientRequestDTO.getEmail());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
=======
    public PatientResponseDTO getPatientById(String id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + id));
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email already exists!"
                    + patientRequestDTO.getEmail());
        }
        if (!checkAge(LocalDate.parse(patientRequestDTO.getDateOfBirth()))) {
            throw new InvalidAgeException("A patient must be above 16 " + patientRequestDTO.getDateOfBirth());
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        billingServiceGrpcClient.createBillingAccount(newPatient.getId(), newPatient.getName(), newPatient.getEmail());
>>>>>>> e036e29 (initial commit for patient-service)
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(String id, PatientRequestDTO patientRequestDTO) {
<<<<<<< HEAD
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists " + patientRequestDTO.getEmail());
        }
=======
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException("A patient with this email already exists! "
                    + patientRequestDTO.getEmail());
        }

>>>>>>> e036e29 (initial commit for patient-service)
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
<<<<<<< HEAD
        var updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
=======
        patient.setRegisteredDate(patient.getRegisteredDate());
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    public static boolean checkAge(LocalDate dateOfBirth) {
        return dateOfBirth.isBefore(LocalDate.now()) && Period.between(dateOfBirth, LocalDate.now()).getYears() >= 16;
    }
>>>>>>> e036e29 (initial commit for patient-service)
}

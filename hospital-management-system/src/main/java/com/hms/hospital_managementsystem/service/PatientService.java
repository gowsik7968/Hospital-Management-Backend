package com.hms.hospital_managementsystem.service;

import com.hms.hospital_managementsystem.dto.PatientDTO;
import com.hms.hospital_managementsystem.entity.Patient;
import com.hms.hospital_managementsystem.exception.ResourceNotFoundException;
import com.hms.hospital_managementsystem.repository.PatientRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    // =========================================================
    // GET ALL PATIENTS
    // =========================================================

    public List<PatientDTO> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // =========================================================
    // GET PATIENT BY ID
    // =========================================================

    public Optional<PatientDTO> getPatientById(String id) {

        return patientRepository.findById(id)
                .map(this::convertToDTO);
    }


    // =========================================================
    // CREATE PATIENT
    // =========================================================

    public PatientDTO createPatient(PatientDTO patientDTO) {

        Patient patient = convertToEntity(patientDTO);

        Patient savedPatient =
                patientRepository.save(patient);

        return convertToDTO(savedPatient);
    }


    // =========================================================
    // UPDATE PATIENT
    // =========================================================

    public PatientDTO updatePatient(
            String id,
            PatientDTO patientDTO) {

        Patient existingPatient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with id: " + id
                                )
                        );


        existingPatient.setName(
                patientDTO.getName()
        );

        existingPatient.setAge(
                patientDTO.getAge()
        );

        existingPatient.setGender(
                patientDTO.getGender()
        );

        existingPatient.setBloodGroup(
                patientDTO.getBloodGroup()
        );

        existingPatient.setPhone(
                patientDTO.getPhone()
        );

        existingPatient.setEmail(
                patientDTO.getEmail()
        );

        existingPatient.setAddress(
                patientDTO.getAddress()
        );

        existingPatient.setStatus(
                patientDTO.getStatus()
        );


        Patient updatedPatient =
                patientRepository.save(existingPatient);


        return convertToDTO(updatedPatient);
    }


    // =========================================================
    // DELETE PATIENT
    // =========================================================

    public void deletePatient(String id) {

        if (!patientRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Patient not found with id: " + id
            );
        }

        patientRepository.deleteById(id);
    }


    // =========================================================
    // ENTITY → DTO
    // =========================================================

    private PatientDTO convertToDTO(
            Patient patient) {

        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getBloodGroup(),
                patient.getPhone(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getStatus()
        );
    }


    // =========================================================
    // DTO → ENTITY
    // =========================================================

    private Patient convertToEntity(
            PatientDTO dto) {

        Patient patient = new Patient();

        patient.setId(
                dto.getId()
        );

        patient.setName(
                dto.getName()
        );

        patient.setAge(
                dto.getAge()
        );

        patient.setGender(
                dto.getGender()
        );

        patient.setBloodGroup(
                dto.getBloodGroup()
        );

        patient.setPhone(
                dto.getPhone()
        );

        patient.setEmail(
                dto.getEmail()
        );

        patient.setAddress(
                dto.getAddress()
        );

        patient.setStatus(
                dto.getStatus()
        );

        return patient;
    }


    // =========================================================
    // PAGINATION
    // =========================================================

    public Page<PatientDTO> getPatientsWithPagination(
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return patientRepository
                .findAll(pageable)
                .map(this::convertToDTO);
    }


    // =========================================================
    // SEARCH PATIENTS
    // =========================================================

    public Page<PatientDTO> searchPatients(
            String name,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return patientRepository
                .findByNameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }
}
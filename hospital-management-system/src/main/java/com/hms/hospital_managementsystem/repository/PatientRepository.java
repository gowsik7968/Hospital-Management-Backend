package com.hms.hospital_managementsystem.repository;

import com.hms.hospital_managementsystem.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository
        extends JpaRepository<Patient, String> {

    Page<Patient> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<Patient> findAll(Pageable pageable);
}
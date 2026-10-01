package com.hms.hospital_managementsystem.repository;

import com.hms.hospital_managementsystem.entity.Prescription;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository
        extends JpaRepository<Prescription, String> {


    // Search by patient name

    Page<Prescription>
    findByPatient_NameContainingIgnoreCase(
            String name,
            Pageable pageable
    );


    // Search by doctor name

    Page<Prescription>
    findByDoctor_NameContainingIgnoreCase(
            String name,
            Pageable pageable
    );


    // Search by medicine name

    Page<Prescription>
    findByMedicines_NameContainingIgnoreCase(
            String medicine,
            Pageable pageable
    );


    // Pagination

    Page<Prescription> findAll(Pageable pageable);
}
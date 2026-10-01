package com.hms.hospital_managementsystem.repository;

import com.hms.hospital_managementsystem.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository
        extends JpaRepository<Doctor, String> {

    Page<Doctor> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<Doctor> findAll(Pageable pageable);
}
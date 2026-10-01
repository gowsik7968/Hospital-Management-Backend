package com.hms.hospital_managementsystem.repository;

import com.hms.hospital_managementsystem.entity.MedicalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String> {

    Page<MedicalRecord> findByPatient_NameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<MedicalRecord> findByDoctor_NameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<MedicalRecord> findByDiagnosisContainingIgnoreCase(
            String diagnosis,
            Pageable pageable
    );

    Page<MedicalRecord> findAll(Pageable pageable);
}
package com.hms.hospital_managementsystem.repository;

import com.hms.hospital_managementsystem.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,String> {

    Page<Appointment> findByPatient_NameContainingIgnoreCase(String name, Pageable pageable);

    Page<Appointment> findByDoctor_NameContainingIgnoreCase(String name, Pageable pageable);

    Page<Appointment> findByStatusIgnoreCase(String status, Pageable pageable);

    Page<Appointment> findAll(Pageable pageable);
}

package com.hms.hospital_managementsystem.controller;

import com.hms.hospital_managementsystem.dto.DoctorDTO;
import com.hms.hospital_managementsystem.service.DoctorService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {

    private final DoctorService doctorService;


    // Constructor

    public DoctorController(
            DoctorService doctorService
    ) {
        this.doctorService = doctorService;
    }


    // =====================================================
    // GET ALL DOCTORS
    // =====================================================

    @GetMapping
    public List<DoctorDTO> getAllDoctors() {

        return doctorService.getAllDoctors();
    }


    // =====================================================
    // GET DOCTOR BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(
            @PathVariable String id
    ) {

        return doctorService
                .getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }


    // =====================================================
    // CREATE DOCTOR
    // =====================================================

    @PostMapping
    public DoctorDTO createDoctor(
            @Valid @RequestBody DoctorDTO doctorDTO
    ) {

        return doctorService.createDoctor(
                doctorDTO
        );
    }


    // =====================================================
    // UPDATE DOCTOR
    // =====================================================

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(
            @PathVariable String id,
            @Valid @RequestBody DoctorDTO doctorDTO
    ) {

        return doctorService.updateDoctor(
                id,
                doctorDTO
        );
    }


    // =====================================================
    // DELETE DOCTOR
    // =====================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable String id
    ) {

        doctorService.deleteDoctor(id);

        return ResponseEntity.noContent().build();
    }
}
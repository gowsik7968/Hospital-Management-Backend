package com.hms.hospital_managementsystem.controller;

import com.hms.hospital_managementsystem.dto.PatientDTO;
import com.hms.hospital_managementsystem.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

    private final PatientService patientService;


    public PatientController(
            PatientService patientService) {

        this.patientService = patientService;
    }


    // =========================================================
    // GET ALL PATIENTS
    // =========================================================

    @GetMapping
    public List<PatientDTO> getAllPatients() {

        return patientService.getAllPatients();
    }


    // =========================================================
    // GET PATIENT BY ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(
            @PathVariable String id) {

        return patientService
                .getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build()
                );
    }


    // =========================================================
    // CREATE PATIENT
    // =========================================================

    @PostMapping
    public PatientDTO createPatient(
            @Valid @RequestBody PatientDTO patientDTO) {

        return patientService.createPatient(
                patientDTO
        );
    }


    // =========================================================
    // UPDATE PATIENT
    // =========================================================

    @PutMapping("/{id}")
    public PatientDTO updatePatient(
            @PathVariable String id,
            @Valid @RequestBody PatientDTO patientDTO) {

        return patientService.updatePatient(
                id,
                patientDTO
        );
    }


    // =========================================================
    // DELETE PATIENT
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
            @PathVariable String id) {

        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }


    // =========================================================
    // PAGINATION
    // =========================================================

    @GetMapping("/page")
    public Page<PatientDTO> getPatientsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return patientService.getPatientsWithPagination(
                page,
                size
        );
    }


    // =========================================================
    // SEARCH
    // =========================================================

    @GetMapping("/search")
    public Page<PatientDTO> searchPatients(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return patientService.searchPatients(
                name,
                page,
                size
        );
    }
}
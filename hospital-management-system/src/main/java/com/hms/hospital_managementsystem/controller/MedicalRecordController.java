package com.hms.hospital_managementsystem.controller;

import com.hms.hospital_managementsystem.dto.MedicalRecordDTO;
import com.hms.hospital_managementsystem.service.MedicalRecordService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(
            MedicalRecordService medicalRecordService) {

        this.medicalRecordService = medicalRecordService;
    }

    // =========================
    // GET ALL
    // GET /api/medical-records
    // =========================

    @GetMapping
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords() {

        return ResponseEntity.ok(
                medicalRecordService.getAllMedicalRecords()
        );
    }

    // =========================
    // PAGINATION
    // GET /api/medical-records/page
    // =========================

    @GetMapping("/page")
    public ResponseEntity<Page<MedicalRecordDTO>>
    getMedicalRecordsWithPagination(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                medicalRecordService
                        .getMedicalRecordsWithPagination(page, size)
        );
    }

    // =========================
    // SEARCH BY PATIENT
    // =========================

    @GetMapping("/search/patient")
    public ResponseEntity<Page<MedicalRecordDTO>>
    searchRecordsByPatient(

            @RequestParam String name,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                medicalRecordService
                        .searchRecordsByPatient(name, page, size)
        );
    }

    // =========================
    // SEARCH BY DOCTOR
    // =========================

    @GetMapping("/search/doctor")
    public ResponseEntity<Page<MedicalRecordDTO>>
    searchRecordsByDoctor(

            @RequestParam String name,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                medicalRecordService
                        .searchRecordsByDoctor(name, page, size)
        );
    }

    // =========================
    // SEARCH BY DIAGNOSIS
    // =========================

    @GetMapping("/search/diagnosis")
    public ResponseEntity<Page<MedicalRecordDTO>>
    searchRecordsByDiagnosis(

            @RequestParam String diagnosis,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                medicalRecordService
                        .searchRecordsByDiagnosis(
                                diagnosis,
                                page,
                                size
                        )
        );
    }

    // =========================
    // GET BY ID
    // =========================

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO>
    getMedicalRecordById(@PathVariable String id) {

        return medicalRecordService
                .getMedicalRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================
    // CREATE
    // POST /api/medical-records
    // =========================

    @PostMapping
    public ResponseEntity<MedicalRecordDTO>
    createMedicalRecord(
            @Valid @RequestBody MedicalRecordDTO dto) {

        MedicalRecordDTO createdRecord =
                medicalRecordService.createMedicalRecord(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdRecord);
    }

    // =========================
    // UPDATE
    // PUT /api/medical-records/{id}
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordDTO>
    updateMedicalRecord(

            @PathVariable String id,

            @Valid @RequestBody MedicalRecordDTO dto) {

        MedicalRecordDTO updatedRecord =
                medicalRecordService
                        .updateMedicalRecord(id, dto);

        return ResponseEntity.ok(updatedRecord);
    }

    // =========================
    // DELETE
    // DELETE /api/medical-records/{id}
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteMedicalRecord(@PathVariable String id) {

        medicalRecordService.deleteMedicalRecord(id);

        return ResponseEntity.noContent().build();
    }
}
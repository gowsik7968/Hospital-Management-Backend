package com.hms.hospital_managementsystem.controller;

import com.hms.hospital_managementsystem.dto.PrescriptionDTO;
import com.hms.hospital_managementsystem.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins = "http://localhost:5173")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(
            PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // GET all prescriptions
    @GetMapping
    public List<PrescriptionDTO> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    // GET prescription by ID
    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(
            @PathVariable String id) {
        return prescriptionService.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE prescription
    @PostMapping
    public PrescriptionDTO createPrescription(
           @Valid @RequestBody PrescriptionDTO prescriptionDTO) {
        return prescriptionService.createPrescription(prescriptionDTO);
    }

    // UPDATE prescription
    @PutMapping("/{id}")
    public PrescriptionDTO updatePrescription(
            @PathVariable String id,
           @Valid @RequestBody PrescriptionDTO prescriptionDTO) {
        return prescriptionService.updatePrescription(id, prescriptionDTO);
    }

    // DELETE prescription
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(
            @PathVariable String id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/page")
    public Page<PrescriptionDTO> getPrescriptionsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return prescriptionService.getPrescriptionsWithPagination(page, size);
    }

    @GetMapping("/search/patient")
    public Page<PrescriptionDTO> searchPrescriptionsByPatient(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return prescriptionService.searchPrescriptionsByPatient(name, page, size);
    }

    @GetMapping("/search/doctor")
    public Page<PrescriptionDTO> searchPrescriptionsByDoctor(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return prescriptionService.searchPrescriptionsByDoctor(name, page, size);
    }

    @GetMapping("/search/medicine")
    public Page<PrescriptionDTO> searchPrescriptionsByMedicine(
            @RequestParam String medicine,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return prescriptionService.searchPrescriptionsByMedicine(medicine, page, size);
    }
}
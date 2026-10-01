package com.hms.hospital_managementsystem.service;

import com.hms.hospital_managementsystem.dto.MedicalRecordDTO;
import com.hms.hospital_managementsystem.entity.Doctor;
import com.hms.hospital_managementsystem.entity.MedicalRecord;
import com.hms.hospital_managementsystem.entity.Patient;
import com.hms.hospital_managementsystem.exception.ResourceNotFoundException;
import com.hms.hospital_managementsystem.repository.DoctorRepository;
import com.hms.hospital_managementsystem.repository.MedicalRecordRepository;
import com.hms.hospital_managementsystem.repository.PatientRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public MedicalRecordService(
            MedicalRecordRepository medicalRecordRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // =========================
    // GET ALL MEDICAL RECORDS
    // =========================

    public List<MedicalRecordDTO> getAllMedicalRecords() {

        return medicalRecordRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // GET MEDICAL RECORD BY ID
    // =========================

    public Optional<MedicalRecordDTO> getMedicalRecordById(String id) {

        return medicalRecordRepository.findById(id)
                .map(this::convertToDTO);
    }

    // =========================
    // CREATE MEDICAL RECORD
    // =========================

    public MedicalRecordDTO createMedicalRecord(MedicalRecordDTO dto) {

        if (medicalRecordRepository.existsById(dto.getId())) {
            throw new RuntimeException(
                    "Medical record already exists with id: " + dto.getId()
            );
        }

        MedicalRecord medicalRecord = convertToEntity(dto);

        MedicalRecord savedRecord =
                medicalRecordRepository.save(medicalRecord);

        return convertToDTO(savedRecord);
    }

    // =========================
    // UPDATE MEDICAL RECORD
    // =========================

    public MedicalRecordDTO updateMedicalRecord(
            String id,
            MedicalRecordDTO dto) {

        MedicalRecord existingRecord =
                medicalRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Medical record not found with id: " + id
                                )
                        );

        Patient patient =
                patientRepository.findById(dto.getPatientId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with id: "
                                                + dto.getPatientId()
                                )
                        );

        Doctor doctor =
                doctorRepository.findById(dto.getDoctorId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Doctor not found with id: "
                                                + dto.getDoctorId()
                                )
                        );

        existingRecord.setPatient(patient);
        existingRecord.setDoctor(doctor);
        existingRecord.setDiagnosis(dto.getDiagnosis());
        existingRecord.setTreatment(dto.getTreatment());
        existingRecord.setNotes(dto.getNotes());
        existingRecord.setDate(dto.getDate());

        MedicalRecord updatedRecord =
                medicalRecordRepository.save(existingRecord);

        return convertToDTO(updatedRecord);
    }

    // =========================
    // DELETE MEDICAL RECORD
    // =========================

    public void deleteMedicalRecord(String id) {

        MedicalRecord existingRecord =
                medicalRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Medical record not found with id: " + id
                                )
                        );

        medicalRecordRepository.delete(existingRecord);
    }

    // =========================
    // PAGINATION
    // =========================

    public Page<MedicalRecordDTO> getMedicalRecordsWithPagination(
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return medicalRecordRepository
                .findAll(pageable)
                .map(this::convertToDTO);
    }

    // =========================
    // SEARCH BY PATIENT
    // =========================

    public Page<MedicalRecordDTO> searchRecordsByPatient(
            String name,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return medicalRecordRepository
                .findByPatient_NameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }

    // =========================
    // SEARCH BY DOCTOR
    // =========================

    public Page<MedicalRecordDTO> searchRecordsByDoctor(
            String name,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return medicalRecordRepository
                .findByDoctor_NameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }

    // =========================
    // SEARCH BY DIAGNOSIS
    // =========================

    public Page<MedicalRecordDTO> searchRecordsByDiagnosis(
            String diagnosis,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return medicalRecordRepository
                .findByDiagnosisContainingIgnoreCase(
                        diagnosis,
                        pageable
                )
                .map(this::convertToDTO);
    }

    // =========================
    // ENTITY → DTO
    // =========================

    private MedicalRecordDTO convertToDTO(
            MedicalRecord record) {

        return new MedicalRecordDTO(
                record.getId(),
                record.getPatient().getId(),
                record.getDoctor().getId(),
                record.getDiagnosis(),
                record.getTreatment(),
                record.getNotes(),
                record.getDate()
        );
    }

    // =========================
    // DTO → ENTITY
    // =========================

    private MedicalRecord convertToEntity(
            MedicalRecordDTO dto) {

        Patient patient =
                patientRepository.findById(dto.getPatientId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with id: "
                                                + dto.getPatientId()
                                )
                        );

        Doctor doctor =
                doctorRepository.findById(dto.getDoctorId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Doctor not found with id: "
                                                + dto.getDoctorId()
                                )
                        );

        MedicalRecord record = new MedicalRecord();

        record.setId(dto.getId());
        record.setPatient(patient);
        record.setDoctor(doctor);
        record.setDiagnosis(dto.getDiagnosis());
        record.setTreatment(dto.getTreatment());
        record.setNotes(dto.getNotes());
        record.setDate(dto.getDate());

        return record;
    }
}
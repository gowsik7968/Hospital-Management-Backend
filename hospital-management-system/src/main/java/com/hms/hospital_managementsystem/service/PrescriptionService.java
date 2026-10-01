package com.hms.hospital_managementsystem.service;

import com.hms.hospital_managementsystem.dto.PrescriptionDTO;
import com.hms.hospital_managementsystem.dto.PrescriptionMedicineDTO;
import com.hms.hospital_managementsystem.entity.Doctor;
import com.hms.hospital_managementsystem.entity.Patient;
import com.hms.hospital_managementsystem.entity.Prescription;
import com.hms.hospital_managementsystem.entity.PrescriptionMedicine;
import com.hms.hospital_managementsystem.exception.ResourceNotFoundException;
import com.hms.hospital_managementsystem.repository.DoctorRepository;
import com.hms.hospital_managementsystem.repository.PatientRepository;
import com.hms.hospital_managementsystem.repository.PrescriptionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;


    public PrescriptionService(
            PrescriptionRepository prescriptionRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.prescriptionRepository =
                prescriptionRepository;

        this.patientRepository =
                patientRepository;

        this.doctorRepository =
                doctorRepository;
    }


    // =========================================================
    // GET ALL PRESCRIPTIONS
    // =========================================================

    public List<PrescriptionDTO> getAllPrescriptions() {

        return prescriptionRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // =========================================================
    // GET PRESCRIPTION BY ID
    // =========================================================

    public Optional<PrescriptionDTO>
    getPrescriptionById(String id) {

        return prescriptionRepository
                .findById(id)
                .map(this::convertToDTO);
    }


    // =========================================================
    // CREATE PRESCRIPTION
    // =========================================================

    public PrescriptionDTO createPrescription(
            PrescriptionDTO dto) {

        String prescriptionId = dto.getId();


        // Generate ID when frontend does not provide one

        if (
                prescriptionId == null ||
                        prescriptionId.trim().isEmpty()
        ) {

            prescriptionId =
                    "PR" + System.currentTimeMillis();

            dto.setId(prescriptionId);
        }


        // Prevent duplicate ID

        if (
                prescriptionRepository
                        .existsById(prescriptionId)
        ) {

            throw new IllegalArgumentException(
                    "Prescription already exists with id: "
                            + prescriptionId
            );
        }


        Prescription prescription =
                convertToEntity(dto);


        Prescription savedPrescription =
                prescriptionRepository
                        .save(prescription);


        return convertToDTO(
                savedPrescription
        );
    }


    // =========================================================
    // UPDATE PRESCRIPTION
    // =========================================================

    public PrescriptionDTO updatePrescription(
            String id,
            PrescriptionDTO dto) {


        Prescription existingPrescription =
                prescriptionRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Prescription not found with id: "
                                                + id
                                )
                        );


        // Find patient

        Patient patient =
                patientRepository
                        .findById(dto.getPatientId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with id: "
                                                + dto.getPatientId()
                                )
                        );


        // Find doctor

        Doctor doctor =
                doctorRepository
                        .findById(dto.getDoctorId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Doctor not found with id: "
                                                + dto.getDoctorId()
                                )
                        );


        // Update basic fields

        existingPrescription.setPatient(
                patient
        );

        existingPrescription.setDoctor(
                doctor
        );

        existingPrescription.setDiagnosis(
                dto.getDiagnosis()
        );

        existingPrescription.setDate(
                dto.getDate()
        );

        existingPrescription.setInstructions(
                dto.getInstructions()
        );

        existingPrescription.setStatus(
                dto.getStatus()
        );


        // Replace medicines

        existingPrescription.setMedicines(
                convertMedicineDTOs(
                        dto.getMedicines()
                )
        );


        Prescription updatedPrescription =
                prescriptionRepository
                        .save(existingPrescription);


        return convertToDTO(
                updatedPrescription
        );
    }


    // =========================================================
    // DELETE PRESCRIPTION
    // =========================================================

    public void deletePrescription(
            String id) {


        Prescription prescription =
                prescriptionRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Prescription not found with id: "
                                                + id
                                )
                        );


        prescriptionRepository.delete(
                prescription
        );
    }


    // =========================================================
    // PAGINATION
    // =========================================================

    public Page<PrescriptionDTO>
    getPrescriptionsWithPagination(
            int page,
            int size) {


        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );


        return prescriptionRepository
                .findAll(pageable)
                .map(this::convertToDTO);
    }


    // =========================================================
    // SEARCH BY PATIENT
    // =========================================================

    public Page<PrescriptionDTO>
    searchPrescriptionsByPatient(
            String name,
            int page,
            int size) {


        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );


        return prescriptionRepository
                .findByPatient_NameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }


    // =========================================================
    // SEARCH BY DOCTOR
    // =========================================================

    public Page<PrescriptionDTO>
    searchPrescriptionsByDoctor(
            String name,
            int page,
            int size) {


        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );


        return prescriptionRepository
                .findByDoctor_NameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }


    // =========================================================
    // SEARCH BY MEDICINE
    // =========================================================

    public Page<PrescriptionDTO>
    searchPrescriptionsByMedicine(
            String medicine,
            int page,
            int size) {


        Pageable pageable =
                PageRequest.of(
                        page,
                        size
                );


        return prescriptionRepository
                .findByMedicines_NameContainingIgnoreCase(
                        medicine,
                        pageable
                )
                .map(this::convertToDTO);
    }


    // =========================================================
    // ENTITY → DTO
    // =========================================================

    private PrescriptionDTO
    convertToDTO(
            Prescription prescription) {


        List<PrescriptionMedicineDTO>
                medicineDTOs = prescription
                .getMedicines()
                .stream()
                .map(this::convertMedicineToDTO)
                .collect(Collectors.toList());


        return new PrescriptionDTO(

                prescription.getId(),

                prescription
                        .getPatient()
                        .getId(),

                prescription
                        .getPatient()
                        .getName(),

                prescription
                        .getDoctor()
                        .getId(),

                prescription
                        .getDoctor()
                        .getName(),

                prescription.getDiagnosis(),

                prescription.getDate(),

                prescription.getInstructions(),

                prescription.getStatus(),

                medicineDTOs
        );
    }


    // =========================================================
    // DTO → ENTITY
    // =========================================================

    private Prescription convertToEntity(
            PrescriptionDTO dto) {


        Patient patient =
                patientRepository
                        .findById(dto.getPatientId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with id: "
                                                + dto.getPatientId()
                                )
                        );


        Doctor doctor =
                doctorRepository
                        .findById(dto.getDoctorId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Doctor not found with id: "
                                                + dto.getDoctorId()
                                )
                        );


        Prescription prescription =
                new Prescription();


        prescription.setId(
                dto.getId()
        );


        prescription.setPatient(
                patient
        );


        prescription.setDoctor(
                doctor
        );


        prescription.setDiagnosis(
                dto.getDiagnosis()
        );


        prescription.setDate(
                dto.getDate()
        );


        prescription.setInstructions(
                dto.getInstructions()
        );


        prescription.setStatus(
                dto.getStatus()
        );


        prescription.setMedicines(
                convertMedicineDTOs(
                        dto.getMedicines()
                )
        );


        return prescription;
    }


    // =========================================================
    // MEDICINE DTO → ENTITY
    // =========================================================

    private List<PrescriptionMedicine>
    convertMedicineDTOs(
            List<PrescriptionMedicineDTO> medicineDTOs) {


        return medicineDTOs
                .stream()
                .map(this::convertMedicineToEntity)
                .collect(Collectors.toList());
    }


    // =========================================================
    // MEDICINE DTO → ENTITY
    // =========================================================

    private PrescriptionMedicine
    convertMedicineToEntity(
            PrescriptionMedicineDTO dto) {


        PrescriptionMedicine medicine =
                new PrescriptionMedicine();


        medicine.setName(
                dto.getName()
        );


        medicine.setDosage(
                dto.getDosage()
        );


        medicine.setFrequency(
                dto.getFrequency()
        );


        medicine.setDuration(
                dto.getDuration()
        );


        return medicine;
    }


    // =========================================================
    // MEDICINE ENTITY → DTO
    // =========================================================

    private PrescriptionMedicineDTO
    convertMedicineToDTO(
            PrescriptionMedicine medicine) {


        return new PrescriptionMedicineDTO(

                medicine.getName(),

                medicine.getDosage(),

                medicine.getFrequency(),

                medicine.getDuration()
        );
    }
}
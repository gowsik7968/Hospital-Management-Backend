package com.hms.hospital_managementsystem.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDTO {

    /*
     * ID is not @NotBlank because the frontend
     * generates/sends it optionally.
     *
     * The service will generate an ID if it is empty.
     */
    private String id;


    @NotBlank(message = "Patient ID is required")
    private String patientId;


    private String patientName;


    @NotBlank(message = "Doctor ID is required")
    private String doctorId;


    private String doctorName;


    @NotBlank(message = "Diagnosis is required")
    @Size(
            min = 2,
            max = 250,
            message = "Diagnosis must be between 2 and 250 characters"
    )
    private String diagnosis;


    @NotBlank(message = "Prescription date is required")
    private String date;


    @Size(
            max = 500,
            message = "Instructions cannot exceed 500 characters"
    )
    private String instructions;


    @NotBlank(message = "Status is required")
    private String status;


    @Valid
    @NotEmpty(
            message = "At least one medicine is required"
    )
    private List<PrescriptionMedicineDTO> medicines =
            new ArrayList<>();


    public PrescriptionDTO() {
    }


    public PrescriptionDTO(
            String id,
            String patientId,
            String patientName,
            String doctorId,
            String doctorName,
            String diagnosis,
            String date,
            String instructions,
            String status,
            List<PrescriptionMedicineDTO> medicines) {

        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.date = date;
        this.instructions = instructions;
        this.status = status;
        this.medicines = medicines;
    }


    // ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    // PATIENT ID

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


    // PATIENT NAME

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    // DOCTOR ID

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }


    // DOCTOR NAME

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    // DIAGNOSIS

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    // DATE

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    // INSTRUCTIONS

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    // STATUS

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // MEDICINES

    public List<PrescriptionMedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(
            List<PrescriptionMedicineDTO> medicines) {

        this.medicines = medicines;
    }
}
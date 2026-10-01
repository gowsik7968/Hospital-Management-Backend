package com.hms.hospital_managementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MedicalRecordDTO {

    @NotBlank(message = "Medical record ID is required")
    private String id;

    @NotBlank(message = "Patient ID is required")
    private String patientId;

    @NotBlank(message = "Doctor ID is required")
    private String doctorId;

    @NotBlank(message = "Diagnosis is required")
    @Size(min = 3, max = 250, message = "Diagnosis must be between 3 and 250 characters")
    private String diagnosis;

    @NotBlank(message = "Treatment is required")
    @Size(min = 3, max = 500, message = "Treatment must be between 3 and 500 characters")
    private String treatment;

    @NotBlank(message = "Notes are required")
    @Size(min = 3, max = 500, message = "Notes must be between 3 and 500 characters")
    private String notes;

    @NotBlank(message = "Date is required")
    private String date;

    public MedicalRecordDTO() {
    }

    public MedicalRecordDTO(
            String id,
            String patientId,
            String doctorId,
            String diagnosis,
            String treatment,
            String notes,
            String date) {

        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
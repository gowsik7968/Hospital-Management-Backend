package com.hms.hospital_managementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false, length = 250)
    private String diagnosis;

    @Column(nullable = false, length = 500)
    private String treatment;

    @Column(nullable = false, length = 500)
    private String notes;

    @Column(nullable = false)
    private String date;

    public MedicalRecord() {
    }

    public MedicalRecord(
            String id,
            Patient patient,
            Doctor doctor,
            String diagnosis,
            String treatment,
            String notes,
            String date) {

        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
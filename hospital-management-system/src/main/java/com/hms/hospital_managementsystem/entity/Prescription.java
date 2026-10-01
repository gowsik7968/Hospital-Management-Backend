package com.hms.hospital_managementsystem.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false, length = 250)
    private String diagnosis;

    @Column(nullable = false)
    private String date;

    @Column(length = 500)
    private String instructions;

    @Column(nullable = false)
    private String status;

    @ElementCollection
    @CollectionTable(
            name = "prescription_medicines",
            joinColumns = @JoinColumn(name = "prescription_id")
    )
    private List<PrescriptionMedicine> medicines = new ArrayList<>();


    public Prescription() {
    }


    public Prescription(
            String id,
            Patient patient,
            Doctor doctor,
            String diagnosis,
            String date,
            String instructions,
            String status,
            List<PrescriptionMedicine> medicines) {

        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
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


    // PATIENT

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    // DOCTOR

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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

    public List<PrescriptionMedicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(
            List<PrescriptionMedicine> medicines) {

        this.medicines = medicines;
    }
}
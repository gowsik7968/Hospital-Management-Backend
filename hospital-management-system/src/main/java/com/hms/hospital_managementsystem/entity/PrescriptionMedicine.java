package com.hms.hospital_managementsystem.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class PrescriptionMedicine {

    private String name;

    private String dosage;

    private String frequency;

    private String duration;


    public PrescriptionMedicine() {
    }


    public PrescriptionMedicine(
            String name,
            String dosage,
            String frequency,
            String duration) {

        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
    }


    // NAME

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // DOSAGE

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }


    // FREQUENCY

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    // DURATION

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
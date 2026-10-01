package com.hms.hospital_managementsystem.dto;

import jakarta.validation.constraints.NotBlank;

public class PrescriptionMedicineDTO {

    @NotBlank(message = "Medicine name is required")
    private String name;

    @NotBlank(message = "Dosage is required")
    private String dosage;

    @NotBlank(message = "Frequency is required")
    private String frequency;

    @NotBlank(message = "Duration is required")
    private String duration;


    public PrescriptionMedicineDTO() {
    }


    public PrescriptionMedicineDTO(
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
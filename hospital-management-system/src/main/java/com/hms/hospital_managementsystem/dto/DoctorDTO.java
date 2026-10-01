package com.hms.hospital_managementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DoctorDTO {

    @NotBlank(message = "Doctor ID is required")
    private String id;

    @NotBlank(message = "Doctor name is required")
    @Size(
            min = 3,
            max = 100,
            message = "Name must be between 3 and 100 characters"
    )
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits"
    )
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Min(
            value = 0,
            message = "Experience cannot be negative"
    )
    private int experience;

    @NotBlank(message = "Status is required")
    private String status;


    // Default Constructor

    public DoctorDTO() {
    }


    // Parameterized Constructor

    public DoctorDTO(
            String id,
            String name,
            String specialization,
            String department,
            String phone,
            String email,
            int experience,
            String status
    ) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.experience = experience;
        this.status = status;
    }


    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
package com.hms.hospital_managementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PatientDTO {

    @NotBlank(message = "Patient ID is required")
    private String id;

    @NotBlank(message = "Patient name is required")
    @Size(
            min = 3,
            max = 100,
            message = "Name must be between 3 to 100 characters"
    )
    private String name;

    @Min(
            value = 1,
            message = "Age must be greater than 0"
    )
    private int age;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Blood group is required")
    private String bloodGroup;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "[0-9]{10}",
            message = "Phone number must contain exactly 10 digits"
    )
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Address is required")
    @Size(
            min = 5,
            max = 250,
            message = "Address must be between 5 and 250 characters"
    )
    private String address;

    @NotBlank(message = "Status is required")
    private String status;


    // Default constructor
    public PatientDTO() {
    }


    // Constructor
    public PatientDTO(
            String id,
            String name,
            int age,
            String gender,
            String bloodGroup,
            String phone,
            String email,
            String address,
            String status) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
    }


    // ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    // Gender
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    // Blood Group
    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    // Phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    // Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
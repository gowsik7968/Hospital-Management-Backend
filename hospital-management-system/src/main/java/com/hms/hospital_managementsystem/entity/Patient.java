package com.hms.hospital_managementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    private String id;

    private String name;

    private int age;

    private String gender;

    private String bloodGroup;

    private String phone;

    private String email;

    private String address;

    private String status;


    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient")
    private List<Billing> bills;


    // Default constructor
    public Patient() {
    }


    // Constructor
    public Patient(
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


    // Appointments
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }


    // Medical Records
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }


    // Prescriptions
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }


    // Bills
    public List<Billing> getBills() {
        return bills;
    }

    public void setBills(List<Billing> bills) {
        this.bills = bills;
    }
}
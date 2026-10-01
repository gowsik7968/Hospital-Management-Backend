package com.hms.hospital_managementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AppointmentDTO {

    @NotBlank(message = "Appointment ID is required")
    private String id;

    @NotBlank(message = "Patient ID is required")
    private String patientId;

    private String patientName;

    @NotBlank(message = "Doctor ID is required")
    private String doctorId;

    private String doctorName;

    private String department;

    @NotBlank(message = "Appointment date is required")
    private String date;

    @NotBlank(message = "Appointment time is required")
    private String time;

    @NotBlank(message = "Appointment status is required")
    private String status;

    @NotBlank(message = "Appointment reason is required")
    @Size(
            min = 3,
            max = 250,
            message = "Reason must be between 3 and 250 characters"
    )
    private String reason;


    public AppointmentDTO() {
    }


    public AppointmentDTO(
            String id,
            String patientId,
            String patientName,
            String doctorId,
            String doctorName,
            String department,
            String date,
            String time,
            String status,
            String reason) {

        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.department = department;
        this.date = date;
        this.time = time;
        this.status = status;
        this.reason = reason;
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


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
package com.hms.hospital_managementsystem.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BillingDTO {

    @NotBlank(message = "Billing ID is required")
    private String id;

    @NotBlank(message = "Patient ID is required")
    private String patientId;

    @Min(
            value = 0,
            message = "Consultation fee cannot be negative"
    )
    private double consultationFee;

    @Min(
            value = 0,
            message = "Medicine fee cannot be negative"
    )
    private double medicineFee;

    @Min(
            value = 0,
            message = "Lab fee cannot be negative"
    )
    private double labFee;

    @Min(
            value = 0,
            message = "Room fee cannot be negative"
    )
    private double roomFee;

    @Min(
            value = 0,
            message = "Total amount cannot be negative"
    )
    private double totalAmount;

    @NotBlank(message = "Payment status is required")
    private String paymentStatus;

    @NotBlank(message = "Billing date is required")
    private String date;


    // =========================================================
    // DEFAULT CONSTRUCTOR
    // =========================================================

    public BillingDTO() {
    }


    // =========================================================
    // FULL CONSTRUCTOR
    // =========================================================

    public BillingDTO(
            String id,
            String patientId,
            double consultationFee,
            double medicineFee,
            double labFee,
            double roomFee,
            double totalAmount,
            String paymentStatus,
            String date) {

        this.id = id;
        this.patientId = patientId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.labFee = labFee;
        this.roomFee = roomFee;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.date = date;
    }


    // =========================================================
    // GETTERS
    // =========================================================

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getMedicineFee() {
        return medicineFee;
    }

    public double getLabFee() {
        return labFee;
    }

    public double getRoomFee() {
        return roomFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getDate() {
        return date;
    }


    // =========================================================
    // SETTERS
    // =========================================================

    public void setId(String id) {
        this.id = id;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public void setMedicineFee(double medicineFee) {
        this.medicineFee = medicineFee;
    }

    public void setLabFee(double labFee) {
        this.labFee = labFee;
    }

    public void setRoomFee(double roomFee) {
        this.roomFee = roomFee;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


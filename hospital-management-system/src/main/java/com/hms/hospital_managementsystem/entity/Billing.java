package com.hms.hospital_managementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "billing")
public class Billing {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private double consultationFee;

    private double medicineFee;

    private double labFee;

    private double roomFee;

    private double totalAmount;

    private String paymentStatus;

    private String date;

    public Billing() {
    }

    public Billing(
            String id,
            Patient patient,
            double consultationFee,
            double medicineFee,
            double labFee,
            double roomFee,
            double totalAmount,
            String paymentStatus,
            String date) {

        this.id = id;
        this.patient = patient;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
        this.labFee = labFee;
        this.roomFee = roomFee;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
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

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getMedicineFee() {
        return medicineFee;
    }

    public void setMedicineFee(double medicineFee) {
        this.medicineFee = medicineFee;
    }

    public double getLabFee() {
        return labFee;
    }

    public void setLabFee(double labFee) {
        this.labFee = labFee;
    }

    public double getRoomFee() {
        return roomFee;
    }

    public void setRoomFee(double roomFee) {
        this.roomFee = roomFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
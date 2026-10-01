package com.hms.hospital_managementsystem.service;

import com.hms.hospital_managementsystem.dto.BillingDTO;
import com.hms.hospital_managementsystem.entity.Billing;
import com.hms.hospital_managementsystem.entity.Patient;
import com.hms.hospital_managementsystem.exception.ResourceNotFoundException;
import com.hms.hospital_managementsystem.repository.BillingRepository;
import com.hms.hospital_managementsystem.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingService {

    private final BillingRepository billingRepository;
    private final PatientRepository patientRepository;

    public BillingService(
            BillingRepository billingRepository,
            PatientRepository patientRepository) {

        this.billingRepository = billingRepository;
        this.patientRepository = patientRepository;
    }

    // =========================================================
    // GET ALL BILLS
    // =========================================================

    public List<BillingDTO> getAllBills() {

        return billingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // =========================================================
    // GET BILL BY ID
    // =========================================================

    public Optional<BillingDTO> getBillById(String id) {

        return billingRepository.findById(id)
                .map(this::convertToDTO);
    }


    // =========================================================
    // CREATE BILL
    // =========================================================

    public BillingDTO createBill(BillingDTO dto) {

        Patient patient = patientRepository
                .findById(dto.getPatientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: "
                                        + dto.getPatientId()
                        )
                );

        Billing billing = new Billing();

        billing.setId(dto.getId());
        billing.setPatient(patient);

        billing.setConsultationFee(
                dto.getConsultationFee()
        );

        billing.setMedicineFee(
                dto.getMedicineFee()
        );

        billing.setLabFee(
                dto.getLabFee()
        );

        billing.setRoomFee(
                dto.getRoomFee()
        );

        double totalAmount =
                dto.getConsultationFee()
                        + dto.getMedicineFee()
                        + dto.getLabFee()
                        + dto.getRoomFee();

        billing.setTotalAmount(totalAmount);

        billing.setPaymentStatus(
                dto.getPaymentStatus()
        );

        billing.setDate(
                dto.getDate()
        );

        Billing savedBill =
                billingRepository.save(billing);

        return convertToDTO(savedBill);
    }


    // =========================================================
    // UPDATE BILL
    // =========================================================

    public BillingDTO updateBill(
            String id,
            BillingDTO dto) {

        Billing existingBill =
                billingRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Bill not found with id: "
                                                + id
                                )
                        );

        Patient patient =
                patientRepository
                        .findById(dto.getPatientId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with id: "
                                                + dto.getPatientId()
                                )
                        );

        existingBill.setPatient(patient);

        existingBill.setConsultationFee(
                dto.getConsultationFee()
        );

        existingBill.setMedicineFee(
                dto.getMedicineFee()
        );

        existingBill.setLabFee(
                dto.getLabFee()
        );

        existingBill.setRoomFee(
                dto.getRoomFee()
        );

        double totalAmount =
                dto.getConsultationFee()
                        + dto.getMedicineFee()
                        + dto.getLabFee()
                        + dto.getRoomFee();

        existingBill.setTotalAmount(totalAmount);

        existingBill.setPaymentStatus(
                dto.getPaymentStatus()
        );

        existingBill.setDate(
                dto.getDate()
        );

        Billing updatedBill =
                billingRepository.save(existingBill);

        return convertToDTO(updatedBill);
    }


    // =========================================================
    // DELETE BILL
    // =========================================================

    public void deleteBill(String id) {

        Billing billing =
                billingRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Bill not found with id: "
                                                + id
                                )
                        );

        billingRepository.delete(billing);
    }


    // =========================================================
    // PAGINATION
    // =========================================================

    public Page<BillingDTO> getBillingsWithPagination(
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return billingRepository
                .findAll(pageable)
                .map(this::convertToDTO);
    }


    // =========================================================
    // SEARCH BY PATIENT
    // =========================================================

    public Page<BillingDTO> searchBillingsByPatient(
            String name,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return billingRepository
                .findByPatient_NameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }


    // =========================================================
    // FILTER BY PAYMENT STATUS
    // =========================================================

    public Page<BillingDTO> filterBillingsByPaymentStatus(
            String paymentStatus,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return billingRepository
                .findByPaymentStatusIgnoreCase(
                        paymentStatus,
                        pageable
                )
                .map(this::convertToDTO);
    }


    // =========================================================
    // ENTITY → DTO
    // =========================================================

    private BillingDTO convertToDTO(Billing billing) {

        String patientId = null;

        if (billing.getPatient() != null) {
            patientId = billing.getPatient().getId();
        }

        return new BillingDTO(
                billing.getId(),
                patientId,
                billing.getConsultationFee(),
                billing.getMedicineFee(),
                billing.getLabFee(),
                billing.getRoomFee(),
                billing.getTotalAmount(),
                billing.getPaymentStatus(),
                billing.getDate()
        );
    }
}
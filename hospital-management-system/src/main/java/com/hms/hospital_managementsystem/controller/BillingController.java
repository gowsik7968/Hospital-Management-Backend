 package com.hms.hospital_managementsystem.controller;
import com.hms.hospital_managementsystem.dto.BillingDTO;
import com.hms.hospital_managementsystem.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@CrossOrigin(origins = "http://localhost:5173")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    // =========================================================
    // GET ALL BILLS
    // =========================================================

    @GetMapping
    public List<BillingDTO> getAllBills() {
        return billingService.getAllBills();
    }


    // =========================================================
    // GET BILL BY ID
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<BillingDTO> getBillById(
            @PathVariable String id) {

        return billingService.getBillById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // =========================================================
    // CREATE BILL
    // =========================================================

    @PostMapping
    public ResponseEntity<BillingDTO> createBill(
            @Valid @RequestBody BillingDTO billingDTO) {

        BillingDTO createdBill =
                billingService.createBill(billingDTO);

        return ResponseEntity.ok(createdBill);
    }


    // =========================================================
    // UPDATE BILL
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<BillingDTO> updateBill(
            @PathVariable String id,
            @Valid @RequestBody BillingDTO billingDTO) {

        BillingDTO updatedBill =
                billingService.updateBill(id, billingDTO);

        return ResponseEntity.ok(updatedBill);
    }


    // =========================================================
    // DELETE BILL
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(
            @PathVariable String id) {

        billingService.deleteBill(id);

        return ResponseEntity.noContent().build();
    }


    // =========================================================
    // PAGINATION
    // =========================================================

    @GetMapping("/page")
    public Page<BillingDTO> getBillingsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return billingService.getBillingsWithPagination(
                page,
                size
        );
    }


    // =========================================================
    // SEARCH BY PATIENT NAME
    // =========================================================

    @GetMapping("/search/patient")
    public Page<BillingDTO> searchBillingsByPatient(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return billingService.searchBillingsByPatient(
                name,
                page,
                size
        );
    }


    // =========================================================
    // FILTER BY PAYMENT STATUS
    // =========================================================

    @GetMapping("/filter/status")
    public Page<BillingDTO> filterBillingsByPaymentStatus(
            @RequestParam String paymentStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return billingService.filterBillingsByPaymentStatus(
                paymentStatus,
                page,
                size
        );
    }
}

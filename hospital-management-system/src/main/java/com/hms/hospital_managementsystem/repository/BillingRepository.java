package com.hms.hospital_managementsystem.repository;

import com.hms.hospital_managementsystem.entity.Billing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository
        extends JpaRepository<Billing, String> {

    Page<Billing> findByPatient_NameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<Billing> findByPaymentStatusIgnoreCase(
            String paymentStatus,
            Pageable pageable
    );
}
package com.hms.hospital_managementsystem.controller;

import com.hms.hospital_managementsystem.dto.AppointmentDTO;
import com.hms.hospital_managementsystem.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }

    // GET ALL APPOINTMENTS
    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {

        return appointmentService.getAllAppointments();
    }

    // GET APPOINTMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointmentById(
            @PathVariable String id) {

        try {

            AppointmentDTO appointment =
                    appointmentService.getAppointmentById(id);

            return ResponseEntity.ok(appointment);

        } catch (RuntimeException exception) {

            return ResponseEntity.notFound().build();
        }
    }

    // CREATE APPOINTMENT
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(
            @Valid @RequestBody AppointmentDTO appointmentDTO) {

        AppointmentDTO createdAppointment =
                appointmentService.createAppointment(
                        appointmentDTO
                );

        return ResponseEntity.ok(createdAppointment);
    }

    // UPDATE APPOINTMENT
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> updateAppointment(
            @PathVariable String id,
            @Valid @RequestBody AppointmentDTO appointmentDTO) {

        try {

            AppointmentDTO updatedAppointment =
                    appointmentService.updateAppointment(
                            id,
                            appointmentDTO
                    );

            return ResponseEntity.ok(updatedAppointment);

        } catch (RuntimeException exception) {

            return ResponseEntity.notFound().build();
        }
    }

    // DELETE APPOINTMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable String id) {

        try {

            appointmentService.deleteAppointment(id);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException exception) {

            return ResponseEntity.notFound().build();
        }
    }

    // PAGINATION
    @GetMapping("/page")
    public Page<AppointmentDTO> getAppointmentsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return appointmentService
                .getAppointmentsWithPagination(page, size);
    }

    // SEARCH BY PATIENT
    @GetMapping("/search/patient")
    public Page<AppointmentDTO> searchAppointmentsByPatient(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return appointmentService
                .searchAppointmentsByPatient(
                        name,
                        page,
                        size
                );
    }

    // SEARCH BY DOCTOR
    @GetMapping("/search/doctor")
    public Page<AppointmentDTO> searchAppointmentsByDoctor(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return appointmentService
                .searchAppointmentsByDoctor(
                        name,
                        page,
                        size
                );
    }

    // FILTER BY STATUS
    @GetMapping("/filter/status")
    public Page<AppointmentDTO> filterAppointmentsByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return appointmentService
                .filterAppointmentsByStatus(
                        status,
                        page,
                        size
                );
    }
}
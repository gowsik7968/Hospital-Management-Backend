package com.hms.hospital_managementsystem.service;

import com.hms.hospital_managementsystem.dto.AppointmentDTO;
import com.hms.hospital_managementsystem.entity.Appointment;
import com.hms.hospital_managementsystem.entity.Doctor;
import com.hms.hospital_managementsystem.entity.Patient;
import com.hms.hospital_managementsystem.repository.AppointmentRepository;
import com.hms.hospital_managementsystem.repository.DoctorRepository;
import com.hms.hospital_managementsystem.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;


    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }


    public List<AppointmentDTO> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public AppointmentDTO getAppointmentById(String id) {

        Appointment appointment = appointmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment not found with ID: " + id
                        )
                );

        return convertToDTO(appointment);
    }


    public AppointmentDTO createAppointment(
            AppointmentDTO dto) {

        Patient patient = patientRepository
                .findById(dto.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with ID: "
                                        + dto.getPatientId()
                        )
                );


        Doctor doctor = doctorRepository
                .findById(dto.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found with ID: "
                                        + dto.getDoctorId()
                        )
                );


        Appointment appointment = new Appointment();

        appointment.setId(dto.getId());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setStatus(dto.getStatus());
        appointment.setReason(dto.getReason());


        Appointment savedAppointment =
                appointmentRepository.save(appointment);


        return convertToDTO(savedAppointment);
    }


    public AppointmentDTO updateAppointment(
            String id,
            AppointmentDTO dto) {

        Appointment appointment = appointmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment not found with ID: " + id
                        )
                );


        Patient patient = patientRepository
                .findById(dto.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with ID: "
                                        + dto.getPatientId()
                        )
                );


        Doctor doctor = doctorRepository
                .findById(dto.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found with ID: "
                                        + dto.getDoctorId()
                        )
                );


        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setStatus(dto.getStatus());
        appointment.setReason(dto.getReason());


        Appointment updatedAppointment =
                appointmentRepository.save(appointment);


        return convertToDTO(updatedAppointment);
    }


    public void deleteAppointment(String id) {

        if (!appointmentRepository.existsById(id)) {

            throw new RuntimeException(
                    "Appointment not found with ID: " + id
            );
        }


        appointmentRepository.deleteById(id);
    }


    public Page<AppointmentDTO> getAppointmentsWithPagination(
            int page,
            int size) {

        PageRequest pageable =
                PageRequest.of(page, size);

        return appointmentRepository
                .findAll(pageable)
                .map(this::convertToDTO);
    }


    public Page<AppointmentDTO> searchAppointmentsByPatient(
            String name,
            int page,
            int size) {

        PageRequest pageable =
                PageRequest.of(page, size);

        return appointmentRepository
                .findByPatient_NameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }


    public Page<AppointmentDTO> searchAppointmentsByDoctor(
            String name,
            int page,
            int size) {

        PageRequest pageable =
                PageRequest.of(page, size);

        return appointmentRepository
                .findByDoctor_NameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }


    public Page<AppointmentDTO> filterAppointmentsByStatus(
            String status,
            int page,
            int size) {

        PageRequest pageable =
                PageRequest.of(page, size);

        return appointmentRepository
                .findByStatusIgnoreCase(
                        status,
                        pageable
                )
                .map(this::convertToDTO);
    }


    private AppointmentDTO convertToDTO(
            Appointment appointment) {

        AppointmentDTO dto = new AppointmentDTO();


        dto.setId(
                appointment.getId()
        );


        dto.setPatientId(
                appointment.getPatient().getId()
        );


        dto.setPatientName(
                appointment.getPatient().getName()
        );


        dto.setDoctorId(
                appointment.getDoctor().getId()
        );


        dto.setDoctorName(
                appointment.getDoctor().getName()
        );


        dto.setDepartment(
                appointment.getDoctor().getDepartment()
        );


        dto.setDate(
                appointment.getDate()
        );


        dto.setTime(
                appointment.getTime()
        );


        dto.setStatus(
                appointment.getStatus()
        );


        dto.setReason(
                appointment.getReason()
        );


        return dto;
    }
}
package com.hms.hospital_managementsystem.service;

import com.hms.hospital_managementsystem.dto.DoctorDTO;
import com.hms.hospital_managementsystem.entity.Doctor;
import com.hms.hospital_managementsystem.exception.ResourceNotFoundException;
import com.hms.hospital_managementsystem.repository.DoctorRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;


    // Constructor

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    // =====================================================
    // GET ALL DOCTORS
    // =====================================================

    public List<DoctorDTO> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // =====================================================
    // GET DOCTOR BY ID
    // =====================================================

    public Optional<DoctorDTO> getDoctorById(String id) {

        return doctorRepository.findById(id)
                .map(this::convertToDTO);
    }


    // =====================================================
    // CREATE DOCTOR
    // =====================================================

    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {

        Doctor doctor = convertToEntity(doctorDTO);

        Doctor savedDoctor =
                doctorRepository.save(doctor);

        return convertToDTO(savedDoctor);
    }


    // =====================================================
    // UPDATE DOCTOR
    // =====================================================

    public DoctorDTO updateDoctor(
            String id,
            DoctorDTO doctorDTO
    ) {

        Doctor existingDoctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Doctor not found with id: " + id
                                )
                        );


        existingDoctor.setName(
                doctorDTO.getName()
        );

        existingDoctor.setSpecialization(
                doctorDTO.getSpecialization()
        );

        existingDoctor.setDepartment(
                doctorDTO.getDepartment()
        );

        existingDoctor.setPhone(
                doctorDTO.getPhone()
        );

        existingDoctor.setEmail(
                doctorDTO.getEmail()
        );

        existingDoctor.setExperience(
                doctorDTO.getExperience()
        );

        existingDoctor.setStatus(
                doctorDTO.getStatus()
        );


        Doctor updatedDoctor =
                doctorRepository.save(existingDoctor);

        return convertToDTO(updatedDoctor);
    }


    // =====================================================
    // DELETE DOCTOR
    // =====================================================

    public void deleteDoctor(String id) {

        if (!doctorRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Doctor not found with id: " + id
            );
        }

        doctorRepository.deleteById(id);
    }


    // =====================================================
    // PAGINATION
    // =====================================================

    public Page<DoctorDTO> getDoctorsWithPagination(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        return doctorRepository
                .findAll(pageable)
                .map(this::convertToDTO);
    }


    // =====================================================
    // SEARCH DOCTORS
    // =====================================================

    public Page<DoctorDTO> searchDoctors(
            String name,
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        return doctorRepository
                .findByNameContainingIgnoreCase(
                        name,
                        pageable
                )
                .map(this::convertToDTO);
    }


    // =====================================================
    // ENTITY → DTO
    // =====================================================

    private DoctorDTO convertToDTO(
            Doctor doctor
    ) {

        return new DoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getDepartment(),
                doctor.getPhone(),
                doctor.getEmail(),
                doctor.getExperience(),
                doctor.getStatus()
        );
    }


    // =====================================================
    // DTO → ENTITY
    // =====================================================

    private Doctor convertToEntity(
            DoctorDTO dto
    ) {

        Doctor doctor = new Doctor();

        doctor.setId(dto.getId());

        doctor.setName(
                dto.getName()
        );

        doctor.setSpecialization(
                dto.getSpecialization()
        );

        doctor.setDepartment(
                dto.getDepartment()
        );

        doctor.setPhone(
                dto.getPhone()
        );

        doctor.setEmail(
                dto.getEmail()
        );

        doctor.setExperience(
                dto.getExperience()
        );

        doctor.setStatus(
                dto.getStatus()
        );

        return doctor;
    }
}
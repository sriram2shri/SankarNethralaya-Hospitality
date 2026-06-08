package com.Hospital.SankarNethralaya.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.Hospital.SankarNethralaya.Dto.AppointmentDto;
import com.Hospital.SankarNethralaya.Dto.Doctordto;
import com.Hospital.SankarNethralaya.Dto.PatientReviewsDto;
import com.Hospital.SankarNethralaya.Entity.Appointment;
import com.Hospital.SankarNethralaya.Entity.Doctor;
import com.Hospital.SankarNethralaya.Repository.ApppointmentRepository;
import com.Hospital.SankarNethralaya.Repository.DoctorRepository;
import com.Hospital.SankarNethralaya.Specification.DoctorSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ApppointmentRepository apppointmentRepository;
    public Map<String,Object> listAll(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Doctor> doctorsList = doctorRepository.findAll(pageable);
        List<Doctordto> doctorDto = doctorsList.stream().map(this::changeToDto).collect(Collectors.toList());
        Map<String,Object> response = new HashMap<>();
        response.put("doctors",doctorDto);
        response.put("DoctorEntries",doctorsList.getTotalElements());
        return response;
    }

    public Doctordto changeToDto(Doctor doctor){
        Doctordto doctorDto = new Doctordto();
        doctorDto.setSno(doctor.getSno());
        doctorDto.setName(doctor.getName());
        doctorDto.setSpecialist(doctor.getSpecialist());

        List<AppointmentDto> appointments = doctor.getAppointment().stream().map(allDatas -> {

            AppointmentDto appointmentDto = new AppointmentDto();
            appointmentDto.setDoctorId(allDatas.getSno());
            appointmentDto.setName(allDatas.getName());
            appointmentDto.setDate(allDatas.getDate());
            appointmentDto.setAppointmenttime(allDatas.getAppointmenttime());
            return appointmentDto;
        }).collect(Collectors.toList());

        doctorDto.setAppointmentDto(appointments);

        List<PatientReviewsDto> patientReviewsDtos = doctor.getPatientReviews().stream().map(reviewsdata -> {
            PatientReviewsDto patientReviewsDto = new PatientReviewsDto();
            patientReviewsDto.setSno(reviewsdata.getId());
            patientReviewsDto.setImg(reviewsdata.getImg());
            patientReviewsDto.setRating(reviewsdata.getRating());
            return patientReviewsDto;
        }).collect(Collectors.toList());

        doctorDto.setPatientReviewsDtos(patientReviewsDtos);
        return doctorDto;
    }


    public Optional<Doctor> findById(int sno) {
        return doctorRepository.findById(sno);
    }

    public List<Doctor> addAll(List<Doctor> doctors) {
        return doctorRepository.saveAll(doctors);
    }

    public Doctor findByDegree(String degree) {
        Doctor regDegree = doctorRepository.findByDegree(degree).orElseThrow(()-> new RuntimeException("The entered degree is not matched"));
        return regDegree;
    }

    public List<Doctor> filterAll(String specialist, String degree,String keyword,Integer experience) {
        Specification<Doctor> spec = Specification.where(DoctorSpecification.hasSpecialist(specialist))
        .and(DoctorSpecification.hasDegree(degree))
        .and(DoctorSpecification.hasKeyword(keyword))
        .and(DoctorSpecification.hasExperience(experience));
        return doctorRepository.findAll(spec);
    }

    public void bookAppointment(AppointmentDto appointmentDto) {
       Doctor doc =  doctorRepository.findById(appointmentDto.getDoctorId()).orElseThrow(()-> new RuntimeException("The doctor id you have selected has not been found"));

       Appointment appointment = new Appointment();
       appointment.setName(appointmentDto.getName());   
       appointment.setDate(appointmentDto.getDate());
       appointment.setAppointmenttime(appointmentDto.getAppointmenttime());
       appointment.setDoctor(doc);

       apppointmentRepository.save(appointment);

    }

    


    
}

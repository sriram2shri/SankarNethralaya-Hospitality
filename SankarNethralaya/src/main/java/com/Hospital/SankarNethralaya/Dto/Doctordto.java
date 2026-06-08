package com.Hospital.SankarNethralaya.Dto;

import java.util.List;

import lombok.Data;

@Data
public class Doctordto {
    private Integer sno;
    private String name;
    private String specialist;
    private List<AppointmentDto> appointmentDto;
    private List<PatientReviewsDto> patientReviewsDtos;
}

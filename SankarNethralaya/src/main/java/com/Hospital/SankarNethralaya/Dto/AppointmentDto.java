package com.Hospital.SankarNethralaya.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppointmentDto {
    
    @NotBlank(message = "Enter the name")
    private String name;
    private Integer doctorId;
    private LocalDate date;
    private String appointmenttime;
}

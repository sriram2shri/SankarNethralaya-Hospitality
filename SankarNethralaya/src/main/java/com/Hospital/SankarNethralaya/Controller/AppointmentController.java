package com.Hospital.SankarNethralaya.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.SankarNethralaya.Dto.AppointmentDto;
import com.Hospital.SankarNethralaya.Service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors/appointment")
public class AppointmentController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<String> bookAppointment (@RequestBody @Valid AppointmentDto appointmentDto){
        doctorService.bookAppointment(appointmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("The appointment has been recorded");
    }
}

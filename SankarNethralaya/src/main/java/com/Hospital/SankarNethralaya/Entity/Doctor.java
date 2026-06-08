package com.Hospital.SankarNethralaya.Entity;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;
    private String name;
    private String specialist;
    private String degree;
    private Integer experience;

    public Doctor(Integer sno, String name, String specialist, String degree, Integer experience) {
        this.sno = sno;
        this.name = name;
        this.specialist = specialist;
        this.degree = degree;
        this.experience = experience;
    }

    @OneToMany
    @JoinColumn(name = "doctor_id")
    @JsonManagedReference
    private List<Appointment> appointment;
    
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,orphanRemoval = true )
    private List<PatientReviews> patientReviews;
}

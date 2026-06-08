package com.Hospital.SankarNethralaya.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PatientReviews{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Min(value = 1)
    @Max(value = 5)
    private int rating;
    private String img;

    public PatientReviews(@Min(1) @Max(5) int rating, String img, Doctor doctor) {
        this.rating = rating;
        this.img = img;
        this.doctor = doctor;
    }

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
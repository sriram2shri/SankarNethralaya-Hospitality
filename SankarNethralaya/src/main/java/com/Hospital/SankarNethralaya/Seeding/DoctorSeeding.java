package com.Hospital.SankarNethralaya.Seeding;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Hospital.SankarNethralaya.Entity.Doctor;
import com.Hospital.SankarNethralaya.Entity.PatientReviews;
import com.Hospital.SankarNethralaya.Repository.DoctorRepository;
@Component
public class DoctorSeeding implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void run(String... args) throws Exception {
        
        if(doctorRepository.count()== 0){
            Doctor doctor = new Doctor(null,"Krishna","Psychiatrist","M.B.B.S",9);
            PatientReviews reviews = new PatientReviews(4,"/Img1.jpg", doctor);
            doctor.setPatientReviews(List.of(reviews));
            doctorRepository.save(doctor);
        }
    }
    
}

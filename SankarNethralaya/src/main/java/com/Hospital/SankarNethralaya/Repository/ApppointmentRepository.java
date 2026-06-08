package com.Hospital.SankarNethralaya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hospital.SankarNethralaya.Entity.Appointment;
@Repository
public interface ApppointmentRepository extends JpaRepository<Appointment,Integer>{
    
}

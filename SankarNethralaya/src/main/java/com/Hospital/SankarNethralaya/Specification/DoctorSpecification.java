package com.Hospital.SankarNethralaya.Specification;

import org.springframework.data.jpa.domain.Specification;

import com.Hospital.SankarNethralaya.Entity.Doctor;

public class DoctorSpecification {
    public static Specification<Doctor> hasSpecialist(String specialist){
        return (root,query,cb) -> specialist == null? null : cb.equal(root.get("specialist"),specialist);
    }

    public static Specification<Doctor> hasDegree(String degree) {
        return (root,query,cb) -> degree == null? null : cb.equal(root.get("degree"),degree);
    }

    public static Specification<Doctor> hasKeyword(String keyword) {

        return (root,query,cb) -> { 
            if(keyword == null) return null;
        return cb.or(cb.like(root.get("specialist"),"%"+keyword.toLowerCase()+"%"),
                                  cb.like(root.get("degree"),"%"+keyword.toLowerCase()+"%"));
        };
  }

  public static Specification<Doctor> hasExperience(Integer experience){
    return (root,query,cb) -> {
    if(experience == null) {
        return null;
    }
    return cb.greaterThanOrEqualTo(root.get("experience"),experience);
  };
}
} 

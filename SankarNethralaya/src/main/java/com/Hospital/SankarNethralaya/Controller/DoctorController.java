package com.Hospital.SankarNethralaya.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Hospital.SankarNethralaya.Entity.Doctor;
import com.Hospital.SankarNethralaya.Service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    @GetMapping("/doctorsList")
    public Map<String,Object> listAll(@RequestParam (defaultValue = "0") int page,@RequestParam(defaultValue = "2")int size){
        return doctorService.listAll(page,size);
    }

    @GetMapping("/{sno}")
    public Optional<Doctor> findById(@PathVariable int sno){
        return doctorService.findById(sno);
    }

    @PostMapping("/addDoctorsList")
    public List<Doctor> addAll(@RequestBody List<Doctor> doctors){
        return doctorService.addAll(doctors);
    }

    @GetMapping("/degree/{degree}")
    public Doctor findByDegree(@PathVariable String degree){
        return doctorService.findByDegree(degree);
    }

    @GetMapping("filter")
    public List<Doctor> filterAll(@RequestParam(required = false) String specialist,@RequestParam(required = false) String degree,@RequestParam(required = false) String keyword,@RequestParam(required = false,defaultValue = "2") Integer experience){
        return doctorService.filterAll(specialist,degree,keyword,experience);
    }
}

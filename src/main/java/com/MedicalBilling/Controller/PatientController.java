package com.MedicalBilling.Controller;

import com.MedicalBilling.DTO.PatientDTO;
import com.MedicalBilling.Entity.PatientEntity;
import com.MedicalBilling.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    
    @GetMapping
    public List<PatientEntity> getAllPatients(){
        return patientRepository.findAll();
    }

    
    @PostMapping
    public PatientEntity createPatient(@RequestBody PatientEntity patient){
        return patientRepository.save(patient);
    }

    
    @GetMapping("/mbi/{mbi}")
    public PatientEntity getPatientByMbi(@PathVariable String mbi){
        return patientRepository.findByPatientMbi(mbi);
    }

    
    @GetMapping("/{id}")
    public PatientDTO getPatient(@PathVariable Long id) {
        PatientEntity entity = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient with ID " + id + " not found"));

        PatientDTO dto = new PatientDTO();
        dto.setPatientId(entity.getPatientId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPatientMbi(entity.getPatientMbi());
        dto.setDob(entity.getDob());
        dto.setGender(entity.getGender());
        dto.setAddress(entity.getAddress());
        dto.setState(entity.getState());
        dto.setPolicyId(entity.getPolicyId());
        dto.setPayerId(entity.getPayerId());

        return dto;
    }
}
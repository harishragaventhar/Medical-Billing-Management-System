package com.MedicalBilling.service;

import com.MedicalBilling.Entity.PatientEntity;
import com.MedicalBilling.Exception.ResourceNotFoundException;
import com.MedicalBilling.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    
    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    
    public PatientEntity getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
    }

    
    public PatientEntity savePatient(PatientEntity patient) {
        
        return patientRepository.save(patient);
    }

    
    public PatientEntity getPatientByMbi(String mbi) {
        PatientEntity patient = patientRepository.findByPatientMbi(mbi);

        if (patient == null) {
            throw new ResourceNotFoundException("Patient with MBI " + mbi + " not found");
        }
        return patient;
    }
}
package com.MedicalBilling.service;

import com.MedicalBilling.Entity.PatientEntity;
import com.MedicalBilling.Repository.PatientRepository;
import com.MedicalBilling.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverageService {

    @Autowired
    private PatientRepository patientRepository;

    
    public String checkCoverage(Long patientId) {
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        String policyId = patient.getPolicyId();

        
        
        if (policyId == null || policyId.isEmpty()) {
            return "INACTIVE: No Policy ID found on file.";
        }

        if (policyId.startsWith("X")) {
            return "DENIED: Policy expired (Simulated Rule).";
        }

        return "ACTIVE: Coverage Verified. Co-Pay: $20.00";
    }
}
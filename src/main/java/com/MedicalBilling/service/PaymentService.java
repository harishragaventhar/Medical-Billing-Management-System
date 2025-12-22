package com.MedicalBilling.service;

import com.MedicalBilling.Entity.ClaimEntity;
import com.MedicalBilling.Repository.ClaimRepository;
import com.MedicalBilling.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private ClaimRepository claimRepository;

    public ClaimEntity processPayment(Long claimId, BigDecimal insurancePaid, BigDecimal allowedAmount) {

        ClaimEntity claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim ID " + claimId + " not found"));

        
        BigDecimal billed = claim.getBilledAmount() != null ? claim.getBilledAmount() : BigDecimal.ZERO;

        
        if (allowedAmount == null) allowedAmount = BigDecimal.ZERO;
        if (insurancePaid == null) insurancePaid = BigDecimal.ZERO;

        
        BigDecimal writeOff = billed.subtract(allowedAmount);

        if (writeOff.compareTo(BigDecimal.ZERO) < 0) {
            writeOff = BigDecimal.ZERO;
        }

        
        BigDecimal patientOwes = allowedAmount.subtract(insurancePaid);

        
        claim.setInsurancePaidAmount(insurancePaid);
        claim.setWriteOff(writeOff);
        claim.setPatientResponsibility(patientOwes);

        
        if (patientOwes.compareTo(BigDecimal.ZERO) > 0) {
            claim.setCurrentStatus("BILLED_TO_PATIENT");
        } else {
            claim.setCurrentStatus("PAID_IN_FULL");
        }

        return claimRepository.save(claim);
    }
}
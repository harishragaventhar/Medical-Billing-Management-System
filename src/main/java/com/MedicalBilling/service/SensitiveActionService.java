package com.MedicalBilling.service;

import com.MedicalBilling.Entity.*;
import com.MedicalBilling.Repository.*;
import com.MedicalBilling.Exception.medicalBillingExpection;
import com.MedicalBilling.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class SensitiveActionService {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private ClaimAuditLogRepository auditRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    private void verifyUserIdentity(String username, String rawPassword) throws medicalBillingExpection {
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null || !passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new medicalBillingExpection("SECURITY ALERT: Invalid Password. Action Denied.");
        }
    }

    
    public ClaimEntity adjustClaimBalance(Long claimId, BigDecimal newWriteOff, String reason, String username, String password) throws medicalBillingExpection {
        
        verifyUserIdentity(username, password);

        
        ClaimEntity claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        
        BigDecimal oldWriteOff = claim.getWriteOff() != null ? claim.getWriteOff() : BigDecimal.ZERO;

        
        claim.setWriteOff(newWriteOff);

        claimRepository.save(claim);

        
        ClaimAuditLog log = new ClaimAuditLog();
        log.setClaimId(claimId);
        log.setUsername(username);
        log.setActionType("FINANCIAL_ADJUSTMENT");
        log.setReason(reason + " (Changed from $" + oldWriteOff + " to $" + newWriteOff + ")");
        log.setTimestamp(LocalDateTime.now());
        auditRepository.save(log);

        return claim;
    }

    
    public Prescription addPrescription(Long claimId, Prescription prescription, String username, String password) throws medicalBillingExpection {
        
        verifyUserIdentity(username, password);

        
        ClaimEntity claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));
        prescription.setClaim(claim);

        
        Prescription saved = prescriptionRepository.save(prescription);

        
        ClaimAuditLog log = new ClaimAuditLog();
        log.setClaimId(claimId);
        log.setUsername(username);
        log.setActionType("PRESCRIPTION_ADDED");
        log.setReason("Added Meds: " + prescription.getMedicationName());
        log.setTimestamp(LocalDateTime.now());
        auditRepository.save(log);

        return saved;
    }
}
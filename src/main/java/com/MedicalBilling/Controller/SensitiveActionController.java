package com.MedicalBilling.Controller;

import com.MedicalBilling.Entity.ClaimEntity;
import com.MedicalBilling.Exception.medicalBillingExpection;
import com.MedicalBilling.service.SensitiveActionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/sensitive")
public class SensitiveActionController {

    @Autowired
    private SensitiveActionService sensitiveService;

    
    @Data
    static class AdjustmentRequest {
        private BigDecimal writeOffAmount;
        private String reason;
        private String username;
        private String password; 
    }

    @PostMapping("/adjust-claim/{id}")
    public ClaimEntity adjustClaim(@PathVariable Long id, @RequestBody AdjustmentRequest request) throws medicalBillingExpection {
        return sensitiveService.adjustClaimBalance(
                id,
                request.getWriteOffAmount(),
                request.getReason(),
                request.getUsername(),
                request.getPassword()
        );
    }
}
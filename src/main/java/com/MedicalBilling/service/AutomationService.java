package com.MedicalBilling.service;

import com.MedicalBilling.Entity.DenialEntity;
import com.MedicalBilling.Repository.DenialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AutomationService {

    
    private static final Logger logger = LoggerFactory.getLogger(AutomationService.class);

    @Autowired
    private DenialRepository denialRepository;

    
    private static final Map<String, String> RULES_ENGINE = new HashMap<>();

    static {
        
        RULES_ENGINE.put("CO-16", "AUTO_ACTION: Generate Missing Info Email");
        RULES_ENGINE.put("CO-22", "AUTO_ACTION: Check COB with Payer");
        RULES_ENGINE.put("CO-29", "AUTO_ACTION: Timely Filing - Write Off");
        RULES_ENGINE.put("PR-1",  "AUTO_ACTION: Move to Patient Responsibility");
        RULES_ENGINE.put("CO-4",  "MANUAL_ACTION: CPT Mismatch - Manager Review");
    }

    
    public String runAutomation(Long denialId) {

        
        DenialEntity denial = denialRepository.findById(denialId).orElse(null);

        if (denial == null) {
            logger.error("Denial ID {} not found in database", denialId);
            return "Error: Denial ID " + denialId + " not found.";
        }

        logger.info("... Bot Analyzing Denial Code: {}", denial.getDenialCode());

        
        String action = RULES_ENGINE.get(denial.getDenialCode());

        if (action != null) {
            
            denial.setAutomationStatus(action);
            denialRepository.save(denial);
            logger.info(">> SUCCESS: Applied Rule. New Status: {}", action);
            return "SUCCESS: Logic Applied. New Status: " + action;
        } else {
            
            denial.setAutomationStatus("MANUAL_REVIEW_NEEDED");
            denialRepository.save(denial);
            logger.warn(">> WARNING: No rule found for code {}. Sent to queue.", denial.getDenialCode());
            return "WARNING: Code Unknown. Sent to Senior AR.";
        }
    }
}
package com.MedicalBilling.service;

import com.MedicalBilling.Entity.DenialEntity;
import com.MedicalBilling.Repository.DenialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenialScheduler {

    private static final Logger logger = LoggerFactory.getLogger(DenialScheduler.class);

    @Autowired
    private DenialRepository denialRepository;

    @Autowired
    private AutomationService automationService;

    
    
    
    @Scheduled(fixedRate = 30000)
    public void autoProcessDenials() {
        logger.info("--- 🕒 SCHEDULER WAKING UP: Checking for new denials... ---");

        
        List<DenialEntity> pendingDenials = denialRepository.findByAutomationStatus("NEW_DENIAL");

        if (pendingDenials.isEmpty()) {
            logger.info(">> No new denials found. Going back to sleep.");
        } else {
            logger.info(">> Found {} new denials! Starting processing...", pendingDenials.size());

            
            for (DenialEntity denial : pendingDenials) {
                try {
                    automationService.runAutomation(denial.getDenialId());
                } catch (Exception e) {
                    logger.error("Error processing denial ID: " + denial.getDenialId(), e);
                }
            }
        }
    }
}
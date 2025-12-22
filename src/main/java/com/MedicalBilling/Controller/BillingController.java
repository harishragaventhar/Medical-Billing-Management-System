package com.MedicalBilling.Controller;

import com.MedicalBilling.Entity.DenialEntity;
import com.MedicalBilling.Repository.DenialRepository;
import com.MedicalBilling.service.AutomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private AutomationService automationService;

    @Autowired
    private DenialRepository denialRepository;


    @GetMapping("/status")
    public String getStatus() {
        return "System is Online. AR Bot is Ready.";
    }


    @PostMapping("/run-bot/{id}")
    public ResponseEntity<String> runBot(@PathVariable Long id) {
        String result = automationService.runAutomation(id);

        if (result.startsWith("Error") || result.startsWith("WARNING")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/denials")
    public List<DenialEntity> getAllDenials() {
        return denialRepository.findAll();
    }
}
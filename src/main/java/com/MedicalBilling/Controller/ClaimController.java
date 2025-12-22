package com.MedicalBilling.Controller;

import com.MedicalBilling.Entity.ClaimEntity;
import com.MedicalBilling.service.DemographicService;
import com.MedicalBilling.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "*")
public class ClaimController {

    @Autowired
    private DemographicService demographicService;

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/upload-card")
    public ClaimEntity uploadInsuranceCard(@RequestParam("file") MultipartFile file) {
        try {

            Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"));
            Path tempFile = tempDir.resolve(file.getOriginalFilename());

            
            file.transferTo(tempFile.toFile());
            System.out.println("File saved temporarily at: " + tempFile.toAbsolutePath());

            
            return demographicService.analyzeInsuranceCard(tempFile.toAbsolutePath().toString());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store file for OCR processing");
        }
    }

    
    @PutMapping("/{id}/process-payment")
    public ClaimEntity processPayment(
            @PathVariable Long id,
            @RequestParam BigDecimal insurancePaid,
            @RequestParam BigDecimal allowedAmount) {

        return paymentService.processPayment(id, insurancePaid, allowedAmount);
    }
}
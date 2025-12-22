package com.MedicalBilling.service;

import com.MedicalBilling.Entity.ClaimEntity;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DemographicService {

    
    public ClaimEntity analyzeInsuranceCard(String imagePath) {
        System.out.println("Initiating Real OCR for: " + imagePath);

        try {
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                return createFallbackClaim("Test Patient", "TEST-000");
            }

            
            Tesseract tesseract = new Tesseract();

            
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
            tesseract.setLanguage("eng");

            
            String result = tesseract.doOCR(imageFile);
            System.out.println("OCR Scanned Text: \n" + result);

            
            return parseScannedText(result);

        } catch (Throwable e) {
            
            System.err.println("OCR FAILED. REASON: " + e.getMessage());
            e.printStackTrace();

            System.out.println("Falling back to Simulation mode...");
            return createFallbackClaim("Simulated User (Fallback)", "SIM-ERROR-001");
        }
    }

    private ClaimEntity parseScannedText(String text) {
        ClaimEntity claim = new ClaimEntity();

        
        
        Pattern namePattern = Pattern.compile("(?i)(Name|Member|Subscriber)\\s*[:#]?\\s*([A-Za-z .]+)");
        Matcher nameMatcher = namePattern.matcher(text);

        if (nameMatcher.find()) {
            claim.setPatientName(nameMatcher.group(2).trim());
        } else {
            
            String[] lines = text.split("\n");
            String extractedName = "Unknown";
            for (String line : lines) {
                line = line.trim();
                
                if (line.isEmpty() || line.toUpperCase().contains("AETNA") || line.toUpperCase().contains("CIGNA")
                        || line.toUpperCase().contains("BLUE CROSS") || line.toUpperCase().contains("INSURANCE")
                        || line.toUpperCase().contains("HEALTH")) {
                    continue;
                }
                
                if (line.matches("^[A-Z][a-z]+ [A-Z][a-z]+.*")) {
                    extractedName = line;
                    break;
                }
            }
            claim.setPatientName(extractedName);
        }

        
        
        
        Pattern policyPattern = Pattern.compile("(?i)(Policy|ID|Member ID|GRP)\\s*[:#]?\\s*([A-Z0-9 -]{5,20})");
        Matcher policyMatcher = policyPattern.matcher(text);

        if (policyMatcher.find()) {
            String rawPolicy = policyMatcher.group(2).trim();
            
            
            claim.setInsurancePolicyNumber(rawPolicy);
        } else {
            
            Pattern fallbackPattern = Pattern.compile("\\b[A-Z]{1,3}[0-9]{6,12}\\b");
            Matcher fallbackMatcher = fallbackPattern.matcher(text);
            if (fallbackMatcher.find()) {
                claim.setInsurancePolicyNumber(fallbackMatcher.group(0));
            } else {
                claim.setInsurancePolicyNumber("SCAN-UNCERTAIN");
            }
        }

        return claim;
    }

    private ClaimEntity createFallbackClaim(String name, String policy) {
        ClaimEntity claim = new ClaimEntity();
        claim.setPatientName(name);
        claim.setInsurancePolicyNumber(policy);
        return claim;
    }
}
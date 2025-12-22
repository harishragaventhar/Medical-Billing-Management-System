package com.MedicalBilling.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DenialDTO {
    private Long claimId; 
    private String denialCode;
    private String description;
    private LocalDate denialDate;
    private String automationStatus;
}
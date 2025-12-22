package com.MedicalBilling.DTO;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ClaimDto {
    private long claimid;
    private LocalDate serviceDate;
    private LocalDate denialDate;
    private Double BilledAmmout;
    private String currentstatus;
    private String PaitentType;

}

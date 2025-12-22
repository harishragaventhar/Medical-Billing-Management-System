package com.MedicalBilling.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 
import java.time.LocalDate;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class PatientDTO {
    private Long patientId;
    private String patientMbi;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String gender;
    private String address;
    private String state;
    private String policyId;
    private String payerId;

    
    
}
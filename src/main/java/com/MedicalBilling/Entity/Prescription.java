package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prescriptions")
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    private String medicationName; 
    private String dosageInstructions; 

    
    @ManyToOne
    @JoinColumn(name = "claim_id")
    private ClaimEntity claim;

    
    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;
}
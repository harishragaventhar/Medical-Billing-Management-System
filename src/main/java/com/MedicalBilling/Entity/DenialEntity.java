package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "denials")
@Data
public class DenialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long denialId;

    
    @Column(name = "denial_code")
    private String denialCode;

    private String description;

    private LocalDate denialDate;

    @Column(name = "automation_status")
    private String automationStatus; 

    
    @ManyToOne
    @JoinColumn(name = "claim_id")
    private ClaimEntity claim;
}
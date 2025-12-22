package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="claims")
@Data
public class ClaimEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long claimId;

    
    @Column(name = "patient_name")
    private String patientName;

    
    @Column(name = "insurance_policy_number")
    private String insurancePolicyNumber;

    private LocalDate serviceDate;
    private LocalDate denialDate;

    
    @Column(name = "billed_amount")
    private BigDecimal billedAmount;

    
    @Column(name = "insurance_paid_amount")
    private BigDecimal insurancePaidAmount;

    
    @Column(name = "patient_responsibility")
    private BigDecimal patientResponsibility;

    
    @Column(name = "write_off")
    private BigDecimal writeOff;

    
    @Column(name = "current_status")
    private String currentStatus;

    @Column(name = "patient_type")
    private String patientType;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ProviderEntity provider;
}
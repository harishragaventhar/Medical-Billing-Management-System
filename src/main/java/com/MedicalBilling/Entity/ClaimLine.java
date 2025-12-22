package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "claim_lines")
@Data
public class ClaimLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineId;

    @Column(name = "line_amount")
    private BigDecimal lineAmount;

    
    @ManyToOne
    @JoinColumn(name = "claim_id")
    private ClaimEntity claim;

    
    @ManyToOne
    @JoinColumn(name = "cpt_code")
    private cptcode CptCode;
}
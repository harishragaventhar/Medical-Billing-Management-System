package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "diagnosis_codes")
@Data
public class DiagnosisCode {

    @Id
    @Column(name = "dx_code", length = 10)
    private String dxCode;

    @Column(name = "description")
    private String description;


    private Double riskScore;
}
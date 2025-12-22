package com.MedicalBilling.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "cpt_code")
@Data
public class cptcode {
    @Id
    @Column(name = "code_id", length = 50)
    private String codeId;

    private String description;

    @Column(name = "standard_cost")
    private BigDecimal standardCost;
}

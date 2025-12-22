package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="insurance_companies")
@Data
public class InsuranceCompany {
    @Id

    @Column(name="payer_id",length = 50)
    private String payerId;
    @Column(name = "payer_name",nullable = false)
    private String payerName;
    @Column(name="address",nullable = false)
    private String Address;
    @Column(name="contact_number")
    private String contact_number;
}

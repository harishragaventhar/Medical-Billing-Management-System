package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "providers")
@Data
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long providerId;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "npi_number", unique = true)
    private Long npiNumber;

    @Column(name = "tax_id", unique = true)
    private String taxId;

    @Column(name = "provider_address")
    private String providerAddress;
}
package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pharmacies")
@Data
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pharmacyId;

    private String pharmacyName;
    private String address;
    private String phoneNumber;
}
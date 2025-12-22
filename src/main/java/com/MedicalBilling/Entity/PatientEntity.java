package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="patients")
@Data
public class PatientEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    
    @Column(name ="patient_mbi", unique = true, length = 22)
    private String patientMbi;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="middle_name")
    private String middleName;

    @Column(name ="last_name")
    private String lastName;

    private LocalDate dob;
    private String gender;

    private String address;
    private String state;
    private String email;

    @Column(name = "policy_id")
    private String policyId;

    @Column(name = "payer_id")
    private String payerId;

    
    
    @OneToMany(mappedBy="patient", cascade=CascadeType.ALL)
    private List<ClaimEntity> claims;
}
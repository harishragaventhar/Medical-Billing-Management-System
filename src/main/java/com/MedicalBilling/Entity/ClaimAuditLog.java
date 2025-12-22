package com.MedicalBilling.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "claim_audit_logs")
@Data
public class ClaimAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @Column(name = "claim_id")
    private Long claimId;

    private String username;      
    private String actionType;    
    private String reason;        

    private LocalDateTime timestamp;
}
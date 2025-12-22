package com.MedicalBilling.Repository;

import com.MedicalBilling.Entity.ClaimLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimLineRepository extends JpaRepository<ClaimLine, Long> {
}

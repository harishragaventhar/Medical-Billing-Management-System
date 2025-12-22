package com.MedicalBilling.Repository;

import com.MedicalBilling.Entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

    
    boolean existsByNpiNumber(Long npiNumber);
}
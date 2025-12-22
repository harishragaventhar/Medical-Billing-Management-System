package com.MedicalBilling.Repository;

import com.MedicalBilling.Entity.DenialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenialRepository extends JpaRepository<DenialEntity, Long> {

    List<DenialEntity> findByDenialCode(String Denialcode);
    List<DenialEntity> findByAutomationStatus(String AutomationStatus);

}

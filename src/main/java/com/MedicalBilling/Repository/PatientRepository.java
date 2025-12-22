package com.MedicalBilling.Repository;

import com.MedicalBilling.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    PatientEntity findByPatientMbi(String paitentMbi);
}

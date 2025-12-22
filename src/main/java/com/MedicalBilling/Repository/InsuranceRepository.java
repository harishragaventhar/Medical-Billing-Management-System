package com.MedicalBilling.Repository;

import com.MedicalBilling.Entity.InsuranceCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceCompany, String> {

}

package com.MedicalBilling.Repository;


import com.MedicalBilling.Entity.cptcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cptCodeRepository extends JpaRepository<cptcode, Long> {

}

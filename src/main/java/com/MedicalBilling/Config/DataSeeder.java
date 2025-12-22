package com.MedicalBilling.Config;

import com.MedicalBilling.Entity.cptcode;
import com.MedicalBilling.Entity.InsuranceCompany;
import com.MedicalBilling.Repository.cptCodeRepository;
import com.MedicalBilling.Repository.InsuranceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(InsuranceRepository insuranceRepo, cptCodeRepository cptRepo) {
        return args -> {
            System.out.println("--- SEEDING DATABASE WITH INITIAL DATA ---");


            if (insuranceRepo.count() == 0) {
                InsuranceCompany bcbs = new InsuranceCompany();
                bcbs.setPayerId("PAYER_BCBS");
                bcbs.setPayerName("Blue Cross Blue Shield");
                bcbs.setAddress("100 Health Lane, NY");
                bcbs.setContact_number("8122687346");
                insuranceRepo.save(bcbs);

                InsuranceCompany aetna = new InsuranceCompany();
                aetna.setPayerId("PAYER_AETNA");
                aetna.setPayerName("Aetna Healthcare");
                aetna.setAddress("500 Insurance Blvd, CT");
                bcbs.setContact_number("8122687346");
                insuranceRepo.save(aetna);
                System.out.println(">> Added Insurance Companies");
            }


            if (cptRepo.count() == 0) {
                cptcode c1 = new cptcode();
                c1.setCodeId("99213");
                c1.setDescription("Office Visit - 15 min");
                c1.setStandardCost(new BigDecimal("150.00"));
                cptRepo.save(c1);

                cptcode c2 = new cptcode();

                c2.setCodeId("SURG_001");
                c2.setDescription("Appendectomy");
                c2.setStandardCost(new BigDecimal("15000.00"));
                cptRepo.save(c2);

                System.out.println(">> Added CPT Codes");
            }
        };
    }
}
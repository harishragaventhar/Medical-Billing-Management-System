package com.MedicalBilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedicalBillingApplication {

	public static void main(String[] args) {

        System.out.println("--- STARTING MEDICAL BILLING AUTOMATION SYSTEM ---");
        SpringApplication.run(MedicalBillingApplication.class, args);
        System.out.println("--- SYSTEM IS LIVE ON PORT 8080 ---");
	}

}

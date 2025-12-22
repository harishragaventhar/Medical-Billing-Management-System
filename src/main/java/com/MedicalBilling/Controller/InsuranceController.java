package com.MedicalBilling.Controller;


import com.MedicalBilling.Entity.InsuranceCompany;
import com.MedicalBilling.Repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/insurance") 
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @GetMapping
    public List<InsuranceCompany> getAllPayers() {
        return insuranceRepository.findAll();
    }
}
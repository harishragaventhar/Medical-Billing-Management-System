package com.MedicalBilling.Controller;

import com.MedicalBilling.Entity.ClaimEntity;
import com.MedicalBilling.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping("/{claimId}")
    public ClaimEntity postPayment(
            @PathVariable Long claimId,
            @RequestParam BigDecimal paid,
            @RequestParam BigDecimal allowed
    ) {
        return paymentService.processPayment(claimId, paid, allowed);
    }
}
package com.MedicalBilling.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class medicalBillingExpection extends RuntimeExpection{

    private static final  Long serialVersionUID= 1L;

    public medicalBillingExpection (String message){
        super (message);
    }
}

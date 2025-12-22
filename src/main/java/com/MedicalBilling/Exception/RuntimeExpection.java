package com.MedicalBilling.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RuntimeExpection extends Exception {
    private  static final long serialVersionUID = 1L;
    public RuntimeExpection(String message) {
        super(message);
    }
}

package com.SelimGezer.GraduationProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Credit not found")
public class CreditNotFound extends RuntimeException{
    public CreditNotFound(String message) {
        super(message);
    }
}

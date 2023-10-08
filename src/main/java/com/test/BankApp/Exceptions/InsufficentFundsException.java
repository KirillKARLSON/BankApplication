package com.test.BankApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficentFundsException extends RuntimeException{
    public InsufficentFundsException(String message){
        super(message);
    }
}

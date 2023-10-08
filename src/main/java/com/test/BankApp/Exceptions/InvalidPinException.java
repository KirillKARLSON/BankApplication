package com.test.BankApp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPinException extends RuntimeException{
    public InvalidPinException(String message){
        super(message);
    }
}

package com.test.BankApp.Handler;

import com.test.BankApp.Exceptions.InsufficentFundsException;
import com.test.BankApp.Exceptions.InvalidPinException;
import com.test.BankApp.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidPinException.class, InsufficentFundsException.class, ResourceNotFoundException.class})
    public ResponseEntity<Object> handleCustomExceptions(Exception e){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(apiError,apiError.getStatus());
    }

}

package com.example.Product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExceptionError.class)
    public ResponseEntity<String> handlerCustomerException(ExceptionError ex, WebRequest request){
        String errorMessage="An error occured:" +ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}

package com.example.architecture.impl.v1.file.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerEx {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){

        String errorMessageDescription = ex.getLocalizedMessage();

        if (errorMessageDescription == null) errorMessageDescription = ex.toString();

        ExceptionModel exceptionModel = new ExceptionModel(new Date(), errorMessageDescription);

        return new ResponseEntity<>(exceptionModel, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.epam.msu.controller;

import com.epam.msu.entity.Response;
import com.epam.msu.exception.CertificateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CertificateNotFoundException.class)
    public ResponseEntity<Response> handleException(CertificateNotFoundException e) {
        Response response = new Response(e.getLocalizedMessage(), 404);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

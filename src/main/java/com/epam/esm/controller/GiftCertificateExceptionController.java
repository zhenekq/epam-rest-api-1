package com.epam.esm.controller;

import com.epam.esm.entity.Response;
import com.epam.esm.exception.certificate.CertificateNotFilledException;
import com.epam.esm.exception.certificate.CertificateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GiftCertificateExceptionController {

    @ExceptionHandler({CertificateNotFoundException.class})
    public ResponseEntity<Response> handleException(CertificateNotFoundException e) {
        Response response = new Response(e.getLocalizedMessage(), 404);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({CertificateNotFilledException.class})
    public ResponseEntity<Response> handleException(CertificateNotFilledException e) {
        Response response = new Response(e.getLocalizedMessage(), 500);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

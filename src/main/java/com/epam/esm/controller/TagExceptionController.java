package com.epam.esm.controller;

import com.epam.esm.entity.Response;
import com.epam.esm.exception.certificate.CertificateNotFilledException;
import com.epam.esm.exception.certificate.CertificateNotFoundException;
import com.epam.esm.exception.tag.TagNotFilledException;
import com.epam.esm.exception.tag.TagNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TagExceptionController {

    @ExceptionHandler({TagNotFoundException.class})
    public ResponseEntity<Response> handleException(TagNotFoundException e) {
        Response response = new Response(e.getLocalizedMessage(), 404);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler({TagNotFilledException.class})
    public ResponseEntity<Response> handleException(TagNotFilledException e) {
        Response response = new Response(e.getLocalizedMessage(), 500);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

package com.epam.msu.controller;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Response;
import com.epam.msu.exception.CertificateNotFoundException;
import com.epam.msu.service.impl.CertificateServiceImpl;
import com.epam.msu.service.validation.Validation;
import com.epam.msu.util.ParametersUtils;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateServiceImpl certificateService;

    @Autowired
    public CertificateController(CertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<CertificateDto>> getAllCertificates(@RequestParam(required = false) String tag,
                                                                   @RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) String sort) throws CertificateNotFoundException {
        List<CertificateDto> certificates = certificateService.getAllCertificates();
        if (tag != null) {
            certificates = ParametersUtils.getCertificatesWithTag(certificates, tag);
        }
        if (name != null) {
            certificates = ParametersUtils.getCertificatesWithName(certificates, name);
        }
        if (sort != null) {
            ParametersUtils.sortCertificatesByName(certificates, sort);
        }

        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable String id) throws CertificateNotFoundException {
        int certificateId = getValidId(id);
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate != null) {
            return new ResponseEntity<CertificateDto>(certificate, HttpStatus.OK);
        } else {
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
    }

    @ExceptionHandler(CertificateNotFoundException.class)
    public Response handleException(CertificateNotFoundException e) {
        return new Response(e.getLocalizedMessage(), HttpStatus.NOT_FOUND.value());
    }


    @PostMapping
    public String addNewCertificate(@RequestBody CertificateDto certificateDto) throws ServiceException, CertificateNotFoundException {
        if (!Validation.isAllFieldsExits(certificateDto)) {
            throw new CertificateNotFoundException("message.exception.new.certificate404");
        }
        certificateService.createNewCertificate(certificateDto);
        return "Certificate successfully created!";
    }

    @PatchMapping("{id}")
    public ResponseEntity<CertificateDto> updateCertificate(@RequestBody CertificateDto certificateDto, @PathVariable String id) throws CertificateNotFoundException {
        int certificateId = getValidId(id);
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate == null) {
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
        certificateDto.setId(certificateId);
        certificateService.updateCertificateById(certificateDto, certificateId);
        return new ResponseEntity<CertificateDto>(certificateDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public String deleteCertificate(@PathVariable String id) throws CertificateNotFoundException {
        int certificateId = getValidId(id);
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate != null) {
            certificateService.deleteCertificateById(certificateId);
            return "Certificate with id = " + id + ", successfully deleted!";
        }
        throw new CertificateNotFoundException("message.exception.certificate404");
    }

    private int getValidId(String id) throws CertificateNotFoundException {
        int certificateId;
        try {
            certificateId = Integer.parseInt(id);
        } catch (Exception e) {
            throw new CertificateNotFoundException("message.exception.invalid.id");
        }
        return certificateId;
    }
}

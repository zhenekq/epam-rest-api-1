package com.epam.msu.controller;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Response;
import com.epam.msu.exception.CertificateNotFoundException;
import com.epam.msu.service.impl.CertificateServiceImpl;
import com.epam.msu.service.validation.Validation;
import com.epam.msu.util.DatabaseProperties;
import com.epam.msu.util.MappingUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateServiceImpl certificateService;

    @Autowired
    public CertificateController(CertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<CertificateDto>> getAllCertificates(@RequestParam(required = false) String tag) throws CertificateNotFoundException {
        List<CertificateDto> certificates = null;
        if (tag == null || tag.isEmpty()) {
            certificates = certificateService.getAllCertificates();
            return new ResponseEntity<List<CertificateDto>>(certificates, HttpStatus.OK);
        }
        certificates = certificateService.getAllCertificatesByTagName(tag);
        if (certificates != null) {
            return new ResponseEntity<List<CertificateDto>>(certificates, HttpStatus.OK);
        }else {
            throw new CertificateNotFoundException("There are no certificates with this tag! (tag = " + tag + ")");
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable String id) throws CertificateNotFoundException {
        int certificateId;
        try {
            certificateId = Integer.parseInt(id);
        }catch (Exception e){
            throw new CertificateNotFoundException("Id is not a number, check your request, id: (" + id + ")");
        }
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate != null) {
            return new ResponseEntity<CertificateDto>(certificate, HttpStatus.OK);
        } else {
            throw new CertificateNotFoundException("Requested certificate not found (id = " + id + ")");
        }
    }

    @ExceptionHandler(CertificateNotFoundException.class)
    public Response handleException(CertificateNotFoundException e) {
        return new Response(e.getMessage(), HttpStatus.NOT_FOUND.value());
    }


    @PostMapping
    public ResponseEntity<CertificateDto> addNewCertificate(@RequestBody CertificateDto certificateDto) throws ServiceException, CertificateNotFoundException {
        if(!Validation.isAllFieldsExits(certificateDto)){
            throw new CertificateNotFoundException("Certificate can't be created, because any field of certificate is null");
        }
        certificateService.createNewCertificate(certificateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CertificateDto> updateCertificate(@RequestBody CertificateDto certificateDto, @PathVariable String id) throws CertificateNotFoundException {
        int certificateId;
        try {
            certificateId = Integer.parseInt(id);
        }catch (Exception e){
            throw new CertificateNotFoundException("Id is not a number, check your request, id: (" + id + ")");
        }
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if(certificate == null) {
            throw new CertificateNotFoundException("Requested certificate not found (id = " + id + ")");
        }
        certificateDto.setId(certificateId);
        certificateService.updateCertificateById(certificateDto, certificateId);
        return new ResponseEntity<CertificateDto>(certificateDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CertificateDto> deleteCertificate(@PathVariable String id) throws CertificateNotFoundException {
        int certificateId;
        try {
            certificateId = Integer.parseInt(id);
        }catch (Exception e){
            throw new CertificateNotFoundException("Id is not a number, check your request, id: (" + id + ")");
        }
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate != null) {
            certificateService.deleteCertificateById(certificateId);
            return ResponseEntity.status(200).body(certificate);
        }
        throw new CertificateNotFoundException("Requested certificate not found (id = " + id + ")");
    }

}

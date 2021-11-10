package com.epam.msu.controller;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Response;
import com.epam.msu.exception.CertificateNotFoundException;
import com.epam.msu.service.impl.CertificateServiceImpl;
import com.epam.msu.service.validation.Validation;
import com.epam.msu.util.MappingUtils;
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
    public ResponseEntity<List<CertificateDto>> getAllCertificates(@RequestParam(required = false) String tag,
                                                                   @RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) String sort,
                                                                   @RequestParam String page) {
        int currentPage = Validation.getValidId(page);
        List<CertificateDto> certificates = certificateService.getFilteredCertificates(name, tag, sort);
        if(certificates == null){
            certificates = certificateService.getPaginatedCertificates(currentPage);
        }
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable String id)  {
        int certificateId = Validation.getValidId(id);
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate != null) {
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } else {
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
    }



    @PostMapping
    public ResponseEntity<CertificateDto> addNewCertificate(@RequestBody CertificateDto certificateDto) throws CertificateNotFoundException {
        if (!Validation.areAllFieldsExistsCertificateDto(certificateDto)) {
            throw new CertificateNotFoundException("message.exception.new.certificate404");
        }
        certificateService.createNewCertificate(certificateDto);
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Certificate> updateCertificate(@RequestBody Certificate certificateDto, @PathVariable String id) throws CertificateNotFoundException {
        int certificateId = Validation.getValidId(id);
        if(!Validation.areAllFieldsExistsCertificate(certificateDto)){
            throw new CertificateNotFoundException("message.exception.new.certificate404");
        }
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate == null) {
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
        certificateDto.setId(certificateId);
        certificateService.updateCertificateById(MappingUtils.mapToCertificateDto(certificateDto), certificateId);
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public String deleteCertificate(@PathVariable String id) throws CertificateNotFoundException {
        int certificateId = Validation.getValidId(id);
        CertificateDto certificate = certificateService.getCertificateById(certificateId);
        if (certificate != null) {
            certificateService.deleteCertificateById(certificateId);
            return "Certificate with id = " + id + ", successfully deleted!";
        }
        throw new CertificateNotFoundException("message.exception.certificate404");
    }
}

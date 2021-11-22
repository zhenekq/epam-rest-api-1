package com.epam.esm.controller;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.certificate.CertificateNotFoundException;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.util.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    private final CertificateServiceImpl certificateService;

    @Autowired
    public GiftCertificateController(CertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<CertificateDto>> getAllCertificates(@RequestParam(required = false) String tag,
                                                                   @RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) String sort,
                                                                   @RequestParam int page) {
        List<CertificateDto> certificates = certificateService.getFilteredCertificates(name, tag, sort);
        if (certificates == null) {
            certificates = certificateService.getPaginatedCertificates(page);
        }
        return new ResponseEntity<>(certificates, HttpStatus.OK);
    }



    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable int id) {
        CertificateDto certificate = certificateService.getCertificateById(id);
        return new ResponseEntity<>(certificate, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CertificateDto> addNewCertificate(@RequestBody CertificateDto certificateDto) throws CertificateNotFoundException {
        certificateService.createNewCertificate(certificateDto);
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Certificate> updateCertificate(@RequestBody Certificate certificateDto, @PathVariable int id) throws CertificateNotFoundException {
        certificateService.updateCertificateById(MappingUtils.mapToCertificateDto(certificateDto), id);
        return new ResponseEntity<>(certificateDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCertificate(@PathVariable int id) {
        certificateService.deleteCertificateById(id);
        return "Certificate with id = " + id + ", successfully deleted!";
    }
}

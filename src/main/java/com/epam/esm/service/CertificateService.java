package com.epam.esm.service;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.certificate.CertificateNotFoundException;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> getAllCertificates();
    CertificateDto getCertificateById(int id);
    CertificateDto createNewCertificate(CertificateDto certificate);
    Certificate updateCertificateById(CertificateDto certificate, int certificateId);
    void deleteCertificateById(int certificateId);
    List<CertificateDto> getPaginatedCertificates(int step) throws CertificateNotFoundException;
    List<CertificateDto> getFilteredCertificates(String name, String tag, String sort);
    List<CertificateDto> getAllCertificatesByTagName(String tagName);
    List<CertificateDto> getCertificatesByNameOrDescription(String text);

}

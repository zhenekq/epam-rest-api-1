package com.epam.msu.service;

import com.epam.msu.dto.CertificateDto;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> getAllCertificates();
    CertificateDto getCertificateById(int id);
    CertificateDto createNewCertificate(CertificateDto certificate);
    void updateCertificateById(CertificateDto certificate, int certificateId);
    void deleteCertificateById(int certificateId);
    List<CertificateDto> getAllCertificatesByTagName(String tagName);
    List<CertificateDto> getCertificatesByNameOrDescription(String text);
}

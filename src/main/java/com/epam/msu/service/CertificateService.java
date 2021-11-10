package com.epam.msu.service;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.exception.CertificateNotFoundException;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> getAllCertificates();
    CertificateDto getCertificateById(int id);
    CertificateDto createNewCertificate(CertificateDto certificate);
    void updateCertificateById(CertificateDto certificate, int certificateId);
    void deleteCertificateById(int certificateId);
    List<CertificateDto> getPaginatedCertificates(int step) throws CertificateNotFoundException;
    List<CertificateDto> getFilteredCertificates(String name, String tag, String sort);
    List<CertificateDto> getAllCertificatesByTagName(String tagName);
    List<CertificateDto> getCertificatesByNameOrDescription(String text);

}

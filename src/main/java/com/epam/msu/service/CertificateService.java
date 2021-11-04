package com.epam.msu.service;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.exception.ServiceException;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> getAllCertificates();
    CertificateDto getCertificateById(int id);
    void createNewCertificate(CertificateDto certificate);
    void updateCertificateById(CertificateDto certificate, int certificateId);
    void deleteCertificateById(int certificateId);
    List<CertificateDto> getAllCertificatesByTagName(String tagName);
}

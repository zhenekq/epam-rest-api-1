package com.epam.msu.service;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.exception.ServiceException;

import java.util.List;

public interface CertificateService {
    List<CertificateDto> getAllCertificates() throws ServiceException;
    CertificateDto getCertificateById(int id) throws ServiceException;
    void createNewCertificate(CertificateDto certificate) throws ServiceException, com.google.protobuf.ServiceException;
    void updateCertificateById(CertificateDto certificate, int certificateId) throws ServiceException;
    void deleteCertificateById(int certificateId) throws ServiceException;
    List<CertificateDto> getAllCertificatesByTagName(String tagName) throws ServiceException;
}

package com.epam.msu.dao;

import com.epam.msu.entity.Certificate;
import com.epam.msu.exception.DaoException;

import java.util.List;

public interface CertificateDao {

    List<Certificate> getAllCertificates();
    Certificate getCertificateById(int id);
    Certificate createNewCertificate(Certificate certificate);
    void updateCertificateById(Certificate certificate,int certificateId);
    void deleteCertificateById(int certificateId);
    List<Certificate> getCertificatesByTagId(int tagId);
    void addInIntermediateTable(int certificateId, int tagId);
    void deleteFromIntermediateTableByCertificateAndTagId(int certificateId, int tagId);
    List<Certificate> getCertificatesByNameOrDescription(String text);
}

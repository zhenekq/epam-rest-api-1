package com.epam.msu.dao;

import com.epam.msu.entity.Certificate;
import com.epam.msu.exception.DaoException;

import java.util.List;

public interface CertificateDao {

    List<Certificate> getAllCertificates() throws DaoException;
    Certificate getCertificateById(int id) throws DaoException;
    Certificate createNewCertificate(Certificate certificate) throws DaoException;
    void updateCertificateById(Certificate certificate,int certificateId) throws DaoException;
    void deleteCertificateById(int certificateId) throws DaoException;
    List<Certificate> getCertificatesByTagId(int tagId) throws DaoException;
    void addInIntermediateTable(int certificateId, int tagId) throws DaoException;
    void deleteFromIntermediateTableByCertificateAndTagId(int certificateId, int tagId) throws DaoException;
}

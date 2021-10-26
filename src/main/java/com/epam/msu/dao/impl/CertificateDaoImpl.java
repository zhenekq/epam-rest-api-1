package com.epam.msu.dao.impl;

import com.epam.msu.dao.CertificateDao;
import com.epam.msu.dao.SqlRequest;
import com.epam.msu.dao.mapper.CertificateMapper;
import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Certificate> getAllCertificates() {
        return jdbcTemplate.query(SqlRequest.getAllCertificates, new CertificateMapper());
    }

    @Override
    public List<Certificate> getCertificatesByTagId(int tagId) {
        return jdbcTemplate.query(SqlRequest.getCertificateByTagId,
                new Object[]{tagId}, new BeanPropertyRowMapper<>(Certificate.class));
    }

    @Override
    public Certificate getCertificateById(int id) {
        return jdbcTemplate.query(SqlRequest.getCertificateById,
                        new Object[]{id}, new BeanPropertyRowMapper<>(Certificate.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public Certificate createNewCertificate(Certificate certificate) {
        jdbcTemplate.update(SqlRequest.createNewCertificate,
                certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate(), certificate.getLastUpdateDate());
        certificate = getLastAddedCertificate();
        return certificate;
    }

    @Override
    public void updateCertificateById(Certificate certificate, int certificateId) {
        jdbcTemplate.update(SqlRequest.updateCertificateById,
                certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getDuration(),
                certificate.getCreateDate(), certificate.getLastUpdateDate(), certificateId);
    }

    @Override
    public void deleteCertificateById(int certificateId) {
        jdbcTemplate.update(SqlRequest.deleteCertificateById, certificateId);
    }

    @Override
    public void deleteFromIntermediateTableByCertificateAndTagId(int certificateId, int tagId) {
        jdbcTemplate.update(SqlRequest.deleteFromIntermediateTable,certificateId, tagId);
    }

    @Override
    public void addInIntermediateTable(int certificateId, int tagId) {
        jdbcTemplate.update(SqlRequest.addIntermediateTable,
                certificateId, tagId);
    }


    private Certificate getLastAddedCertificate(){
        return jdbcTemplate.query(SqlRequest.getLastAddedCertificate, new Object[]{}, new CertificateMapper())
                .stream()
                .findAny()
                .orElse(null);
    }
}

package com.epam.msu.dao.impl;

import com.epam.msu.dao.CertificateDao;
import com.epam.msu.dao.SqlRequest;
import com.epam.msu.entity.Certificate;
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
        return jdbcTemplate.query(SqlRequest.getAllCertificates, new BeanPropertyRowMapper<>(Certificate.class));
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
        jdbcTemplate.update(SqlRequest.deleteFromIntermediateTable, certificateId, tagId);
    }

    @Override
    public void addInIntermediateTable(int certificateId, int tagId) {
        jdbcTemplate.update(SqlRequest.addIntermediateTable,
                certificateId, tagId);
    }

    @Override
    public List<Certificate> getCertificatesByNameOrDescription(String text) {
        return jdbcTemplate.query("SELECT * FROM certificate where name LIKE '%" + text + "%' or description LIKE '%" + text + "%'", new BeanPropertyRowMapper<>(Certificate.class));

    }

    private Certificate getLastAddedCertificate() {
        return jdbcTemplate.query(SqlRequest.getLastAddedCertificate, new BeanPropertyRowMapper<>(Certificate.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Certificate> getPaginatedCertificates(int step) {
        return jdbcTemplate.query(SqlRequest.getPaginatedCertificates, new BeanPropertyRowMapper<>(Certificate.class), 4 + (4 * (step - 2)));
    }

    @Override
    public List<Certificate> getFilteredCertificates(String... parameters) {
        return jdbcTemplate.query("SELECT * FROM certificate order by name " + parameters[0], new BeanPropertyRowMapper<>(Certificate.class));
    }
}

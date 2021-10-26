package com.epam.msu.dao.mapper;

import com.epam.msu.entity.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CertificateMapper implements RowMapper<Certificate> {
    @Override
    public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(resultSet.getInt("id"));
        certificate.setName(resultSet.getString("name"));
        certificate.setDescription(resultSet.getString("description"));
        certificate.setPrice(resultSet.getInt("price"));
        certificate.setDuration(resultSet.getInt("duration"));
        certificate.setCreateDate(resultSet.getTimestamp("create_date"));
        certificate.setLastUpdateDate(resultSet.getTimestamp("last_update_date"));

        return certificate;
    }
}

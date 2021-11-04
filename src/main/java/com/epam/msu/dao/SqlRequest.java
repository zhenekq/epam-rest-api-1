package com.epam.msu.dao;

public final class SqlRequest {

    private SqlRequest() {
    }

    public static final String getAllCertificates = "SELECT * FROM certificate";
    public static final String getCertificateByTagId = "select id, name, description, price, duration, create_date, last_update_date from certificate join certificate_tag on certificate_tag.certificate_id = certificate.id where tag_id = ?";
    public static final String createNewCertificate = "INSERT INTO certificate (name, description, price, duration, create_date, last_update_date) VALUES (?,?,?,?,?,?)";
    public static final String updateCertificateById = "UPDATE certificate set name=?, description=?, price=?,duration=?,create_date=?,last_update_date=? where id = ?";
    public static final String deleteCertificateById = "DELETE FROM certificate where id = ?";
    public static final String deleteFromIntermediateTable = "DELETE FROM certificate_tag where certificate_id=? and tag_id=?";
    public static final String addIntermediateTable = "INSERT INTO certificate_tag (certificate_id, tag_id) VALUES (?, ?)";
    public static final String getLastAddedCertificate = "SELECT * FROM certificate order by id desc limit 1";
    public static final String getCertificateById = "Select * from certificate where id = ?";

    public static final String createTag = "INSERT INTO tag (name) values(?)";
    public static final String getTagById = "SELECT * FROM tag where id = ?";
    public static final String getLastAddedTag = "SELECT * FROM tag order by id desc limit 1";
    public static final String getTagByName = "SELECT * FROM tag where name = ?";
    public static final String getAllTags = "SELECT * FROM tag";
    public static final String getTagByCertificateId = "SELECT tag.id, tag.name from tag join certificate_tag on certificate_tag.tag_id = tag.id where certificate_tag.certificate_id = ?";
    public static final String getTagByTagName = "SELECT * FROM tag where name = ?";

}

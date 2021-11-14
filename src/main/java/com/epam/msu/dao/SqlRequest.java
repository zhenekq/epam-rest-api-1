package com.epam.msu.dao;

public final class SqlRequest {

    private SqlRequest() {
    }

    public static final String ALL_CERTIFICATES = "SELECT * FROM certificate";
    public static final String CERTIFICATE_BY_TAG_ID = "select id, name, description, price, duration, create_date, last_update_date from certificate join certificate_tag on certificate_tag.certificate_id = certificate.id where tag_id = ?";
    public static final String CREATE_NEW_CERTIFICATE = "INSERT INTO certificate (name, description, price, duration, create_date, last_update_date) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_CERTIFICATE_BY_ID = "UPDATE certificate set name=?, description=?, price=?,duration=?,create_date=?,last_update_date=? where id = ?";
    public static final String DELETE_CERTIFICATE_BY_ID = "DELETE FROM certificate where id = ?";
    public static final String DELETE_FROM_INTERMEDIATE_TABLE = "DELETE FROM certificate_tag where certificate_id=? and tag_id=?";
    public static final String ADD_IN_INTERMEDIATE_TABLE = "INSERT INTO certificate_tag (certificate_id, tag_id) VALUES (?, ?)";
    public static final String LAST_ADDED_CERTIFICATE = "SELECT * FROM certificate order by id desc limit 1";
    public static final String CERTIFICATE_BY_ID = "Select * from certificate where id = ?";
    public static final String PAGINATE_CERTIFICATES = "SELECT * FROM certificate limit 4 offset ?";

    public static final String CREATE_TAG = "INSERT INTO tag (name) values(?)";
    public static final String GET_TAG_BY_ID = "SELECT * FROM tag where id = ?";
    public static final String GET_LAST_ADDED_TAG = "SELECT * FROM tag order by id desc limit 1";
    public static final String GET_TAG_BY_NAME = "SELECT * FROM tag where name = ?";
    public static final String GET_ALL_TAGS = "SELECT * FROM tag";
    public static final String GET_TAGS_BY_CERTIFICATE_ID = "SELECT tag.id, tag.name from tag join certificate_tag on certificate_tag.tag_id = tag.id where certificate_tag.certificate_id = ?";

}

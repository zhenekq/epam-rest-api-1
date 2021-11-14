package com.epam.msu.dao.impl;

import com.epam.msu.dao.SqlRequest;
import com.epam.msu.dao.TagDao;
import com.epam.msu.entity.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    private final JdbcTemplate jdbcTemplate;

    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Tag createTag(Tag tag) {
        jdbcTemplate.update(SqlRequest.CREATE_TAG, tag.getName());
        tag = getLastAddedTag();
        return tag;
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.query(SqlRequest.GET_TAG_BY_ID, new BeanPropertyRowMapper<>(Tag.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    private Tag getLastAddedTag() {
        return jdbcTemplate.query(SqlRequest.GET_LAST_ADDED_TAG, new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return jdbcTemplate.query(SqlRequest.GET_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class), name)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SqlRequest.GET_ALL_TAGS, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public List<Tag> getTagsByCertificateId(int certificateId) {
        return jdbcTemplate.query(SqlRequest.GET_TAGS_BY_CERTIFICATE_ID, new BeanPropertyRowMapper<>(Tag.class), certificateId);
    }

    @Override
    public Tag getTagByTagName(String name) {
        return jdbcTemplate.query(SqlRequest.GET_TAG_BY_NAME, new BeanPropertyRowMapper<>(Tag.class), name)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void updateTag(String name, int tagId) {
        jdbcTemplate.update("UPDATE tag set name = ? where id=?", new BeanPropertyRowMapper<>(Tag.class), name, tagId);
    }

    @Override
    public void deleteTagById(int id) {
        jdbcTemplate.update("DELETE FROM tag where id = ?", new BeanPropertyRowMapper<>(Tag.class),id);
    }
}

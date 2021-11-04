package com.epam.msu.dao.impl;

import com.epam.msu.dao.SqlRequest;
import com.epam.msu.dao.TagDao;
import com.epam.msu.dao.mapper.CertificateMapper;
import com.epam.msu.entity.Tag;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
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
        jdbcTemplate.update(SqlRequest.createTag, tag.getName());
        tag = getLastAddedTag();
        return tag;
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.query(SqlRequest.getTagById, new Object[]{id}, new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    private Tag getLastAddedTag(){
        return jdbcTemplate.query(SqlRequest.getLastAddedTag, new Object[]{}, new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return jdbcTemplate.query(SqlRequest.getTagByName, new Object[]{name}, new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SqlRequest.getAllTags, new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag getTagByCertificateId(int certificateId) {
        return jdbcTemplate.query(SqlRequest.getTagByCertificateId, new Object[]{certificateId}, new BeanPropertyRowMapper<>(Tag.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public Tag getTagByTagName(String name) {
        return jdbcTemplate.query(SqlRequest.getTagByTagName, new Object[]{name}, new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }
}

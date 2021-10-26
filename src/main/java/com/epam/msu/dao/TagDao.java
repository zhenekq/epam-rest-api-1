package com.epam.msu.dao;

import com.epam.msu.entity.Tag;
import com.epam.msu.exception.DaoException;

import java.util.List;

public interface TagDao {
    Tag createTag(Tag tag) throws DaoException;
    void updateTag(Tag tag) throws DaoException;
    Tag getTagById(int id) throws DaoException;
    Tag getTagByName(String name) throws DaoException;
    Tag getTagByCertificateId(int certificateId) throws DaoException;
    Tag getTagByTagName(String name) throws DaoException;
    List<Tag> getAllTags() throws DaoException;
}

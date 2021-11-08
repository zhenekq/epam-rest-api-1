package com.epam.msu.dao;

import com.epam.msu.entity.Tag;
import com.epam.msu.exception.DaoException;

import java.util.List;

public interface TagDao {
    Tag createTag(Tag tag);
    Tag getTagById(int id);
    Tag getTagByName(String name);
    List<Tag> getTagsByCertificateId(int certificateId);
    Tag getTagByTagName(String name);
    List<Tag> getAllTags();
}

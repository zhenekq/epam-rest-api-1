package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagDao {
    Tag createTag(Tag tag);
    Tag getTagById(int id);
    Tag getTagByName(String name);
    List<Tag> getTagsByCertificateId(int certificateId);
    Tag getTagByTagName(String name);
    List<Tag> getAllTags();
    void updateTag(String name, int tagId);
    void deleteTagById(int id);
    void deleteFromIntermediateTableByTagId(int tagId);
}

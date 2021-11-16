package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(int id);
    List<Tag> getAllTags();
    void createTag(Tag tag);
    void updateTag(Tag tag, int id);
    Tag deleteTagById(int id);
}

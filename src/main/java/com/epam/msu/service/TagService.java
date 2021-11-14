package com.epam.msu.service;

import com.epam.msu.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTagById(int id);
    List<Tag> getAllTags();
    void createTag(Tag tag);
    void updateTag(String name, int id);
    Tag deleteTagById(int id);
}

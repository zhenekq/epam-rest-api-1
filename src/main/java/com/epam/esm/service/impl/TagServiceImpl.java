package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.certificate.CertificateNotFoundException;
import com.epam.esm.exception.tag.TagNotFilledException;
import com.epam.esm.exception.tag.TagNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    @Override
    public Tag getTagById(int id) {
        Tag tag = tagDao.getTagById(id);
        if (tag != null) {
            return tag;
        } else {
            throw new TagNotFoundException("message.exception.tag404");
        }
    }

    @Override
    public void createTag(Tag tag) {
        if (tag.getName() != null && isUniqueTag(tag)) {
            tagDao.createTag(tag);
            return;
        }
        throw new TagNotFilledException("message.exception.tag500");
    }

    @Override
    public void updateTag(Tag tag, int id) {
        String name = tag.getName();
        if(name == null || name.isEmpty()){
            throw new TagNotFilledException("message.exception.tag500");
        }
        Tag tagDatabase = tagDao.getTagById(id);
        if (tagDatabase != null) {
            tagDao.updateTag(name, id);
            return;
        }
        throw new TagNotFoundException("message.exception.tag404");
    }

    @Override
    public Tag deleteTagById(int id) {
        Tag tag = tagDao.getTagById(id);
        if (tag != null) {
            tagDao.deleteTagById(id);
            tagDao.deleteFromIntermediateTableByTagId(id);
            return tag;
        }
        throw new TagNotFoundException("message.exception.tag404");
    }

    private boolean isUniqueTag(Tag tag) {
        Tag existingTag = tagDao.getTagByName(tag.getName());
        return existingTag == null;
    }
}

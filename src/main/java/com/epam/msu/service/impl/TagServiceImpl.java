package com.epam.msu.service.impl;

import com.epam.msu.dao.TagDao;
import com.epam.msu.entity.Tag;
import com.epam.msu.exception.CertificateNotFoundException;
import com.epam.msu.service.TagService;
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
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
    }

    @Override
    public void createTag(Tag tag) {
        if (tag != null && isUniqueTag(tag)) {
            tagDao.createTag(tag);
            return;
        }
        throw new CertificateNotFoundException("message.exception.certificate404");
    }

    @Override
    public void updateTag(String name, int id) {
        Tag tag = tagDao.getTagById(id);
        if (tag != null) {
            tagDao.updateTag(name, id);
        } else {
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
    }

    @Override
    public Tag deleteTagById(int id) {
        Tag tag = tagDao.getTagById(id);
        if (tag != null) {
            tagDao.deleteTagById(id);
            return tag;
        }
        throw new CertificateNotFoundException("message.exception.certificate404");
    }

    private boolean isUniqueTag(Tag tag) {
        Tag existingTag = tagDao.getTagByName(tag.getName());
        return existingTag == null;
    }
}

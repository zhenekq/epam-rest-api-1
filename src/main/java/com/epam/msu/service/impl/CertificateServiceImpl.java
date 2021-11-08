package com.epam.msu.service.impl;

import com.epam.msu.dao.impl.CertificateDaoImpl;
import com.epam.msu.dao.impl.TagDaoImpl;
import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Tag;
import com.epam.msu.service.CertificateService;
import com.epam.msu.util.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private final CertificateDaoImpl certificateDao;
    private final TagDaoImpl tagDao;

    @Autowired
    public CertificateServiceImpl(CertificateDaoImpl certificateDao, TagDaoImpl tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }

    @Override
    public List<CertificateDto> getAllCertificates() {
        List<Certificate> certificateList = certificateDao.getAllCertificates();
        List<CertificateDto> certificatesDto = MappingUtils.mapToListCertificateDto(certificateList);
        List<Tag> tags = new ArrayList<>();
        for (CertificateDto certificate : certificatesDto) {
            List<Tag> tag = tagDao.getTagsByCertificateId((int) certificate.getId());
            certificate.setTag(tag);

        }
        ;
        return certificatesDto;
    }

    @Override
    public CertificateDto getCertificateById(int id) {
        Certificate certificate = certificateDao.getCertificateById(id);
        CertificateDto certificateDto = null;
        if (certificate != null) {
            certificateDto = MappingUtils.mapToCertificateDto(certificate);
            List<Tag> certificateTag = tagDao.getTagsByCertificateId(id);
            certificateDto.setTag(certificateTag);
            return certificateDto;
        }
        return null;
    }

    @Override
    public void createNewCertificate(CertificateDto certificate) {
        List<Tag> tags = certificate.getTag();
        int tagId = 0;
        certificate = MappingUtils.mapToCertificateDto(certificateDao.createNewCertificate(MappingUtils.mapToCertificate(certificate)));
        int certificateId = certificateId = (int) certificate.getId();
        for (Tag tag : tags) {
            if (isUniqueTag(tag)) {
                tag = tagDao.createTag(tag);
                tagId = (int) tag.getId();
                certificateDao.addInIntermediateTable(certificateId, tagId);
            } else {
                String tagName = tag.getName();
                Tag existsTag = tagDao.getTagByName(tagName);
                tagId = (int) existsTag.getId();
                certificateDao.addInIntermediateTable(certificateId, tagId);
            }
        }
    }

    private boolean isUniqueTag(Tag tag) {
        List<Tag> tags = tagDao.getAllTags();
        for (Tag el : tags) {
            if (el.getName().equals(tag.getName())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void updateCertificateById(CertificateDto certificateDto, int certificateId) {
        Certificate certificate = MappingUtils.mapToCertificate(certificateDto);
        certificateDao.updateCertificateById(certificate, certificateId);
    }

    @Override
    public void deleteCertificateById(int certificateId) {
        List<Tag> tags = tagDao.getTagsByCertificateId(certificateId);
        certificateDao.deleteCertificateById(certificateId);
        for (Tag tag : tags) {
            int tagId = (int) tag.getId();
            certificateDao.deleteFromIntermediateTableByCertificateAndTagId(certificateId, tagId);
        }
    }

    @Override
    public List<CertificateDto> getAllCertificatesByTagName(String tagName) {
        List<CertificateDto> certificates = null;
        Tag tag = tagDao.getTagByTagName(tagName);
        List<Tag> tags = new ArrayList<>();
        tags.add(tag);
        int tagId = 0;
        if (tag != null) {
            tagId = (int) tag.getId();
            certificates = MappingUtils.mapToListCertificateDto(certificateDao.getCertificatesByTagId(tagId));
            certificates.forEach((p) -> p.setTag(tags));
        }
        return certificates;
    }

    @Override
    public List<CertificateDto> getCertificatesByNameOrDescription(String text) {
        List<CertificateDto> certificateDto = MappingUtils.mapToListCertificateDto(certificateDao.getCertificatesByNameOrDescription(text));
        certificateDto.forEach((p) -> p.setTag(tagDao.getTagsByCertificateId((int) p.getId())));
        return certificateDto;
    }
}

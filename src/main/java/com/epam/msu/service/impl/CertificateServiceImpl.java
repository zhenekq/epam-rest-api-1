package com.epam.msu.service.impl;

import com.epam.msu.dao.impl.CertificateDaoImpl;
import com.epam.msu.dao.impl.TagDaoImpl;
import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.entity.Tag;
import com.epam.msu.exception.CertificateNotFoundException;
import com.epam.msu.service.CertificateService;
import com.epam.msu.service.validation.Validation;
import com.epam.msu.util.MappingUtils;
import com.google.protobuf.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (CertificateDto certificate : certificatesDto) {
            Tag tag = tagDao.getTagByCertificateId((int) certificate.getId());
            certificate.setTag(tag);
        }
        return certificatesDto;
    }

    @Override
    public CertificateDto getCertificateById(int id) {
        Certificate certificate = certificateDao.getCertificateById(id);
        CertificateDto certificateDto = null;
        if (certificate != null) {
            certificateDto = MappingUtils.mapToCertificateDto(certificate);
            Tag certificateTag = tagDao.getTagByCertificateId(id);
            certificateDto.setTag(certificateTag);
            return certificateDto;
        }
        return null;
    }

    @Override
    public void createNewCertificate(CertificateDto certificate) throws ServiceException {
        Tag tag = certificate.getTag();
        int tagId = 0;
        int certificateId = 0;
        if (isUniqueTag(tag)) {
            tag = tagDao.createTag(tag);
            tagId = (int) tag.getId();
        } else {
            String tagName = tag.getName();
            Tag existsTag = tagDao.getTagByName(tagName);
            tagId = (int) existsTag.getId();
        }

        certificate = MappingUtils.mapToCertificateDto(certificateDao.createNewCertificate(MappingUtils.mapToCertificate(certificate)));
        certificateId = (int) certificate.getId();
        certificateDao.addInIntermediateTable(certificateId, tagId);
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
        Tag tag = tagDao.getTagByCertificateId(certificateId);
        certificateDao.deleteCertificateById(certificateId);
        int tagId = (int) tag.getId();
        certificateDao.deleteFromIntermediateTableByCertificateAndTagId(certificateId, tagId);
    }

    @Override
    public List<CertificateDto> getAllCertificatesByTagName(String tagName) {
        List<CertificateDto> certificates = null;
        Tag tag = tagDao.getTagByTagName(tagName);
        int tagId = 0;
        if (tag != null) {
            tagId = (int) tag.getId();
            certificates = MappingUtils.mapToListCertificateDto(certificateDao.getCertificatesByTagId(tagId));
            certificates.forEach((p) -> p.setTag(tag));
        }
        return certificates;
    }

}

package com.epam.esm.service.impl;

import com.epam.esm.dao.impl.CertificateDaoImpl;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.certificate.CertificateNotFilledException;
import com.epam.esm.exception.certificate.CertificateNotFoundException;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.validation.Validation;
import com.epam.esm.util.MappingUtils;
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
            List<Tag> tag = tagDao.getTagsByCertificateId(certificate.getId());
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
            List<Tag> certificateTag = tagDao.getTagsByCertificateId(id);
            certificateDto.setTag(certificateTag);
        }else{
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
        return certificateDto;
    }

    @Override
    public CertificateDto createNewCertificate(CertificateDto certificate) {
        if (!Validation.areAllFieldsExistsCertificateDto(certificate)) {
            throw new CertificateNotFilledException("message.exception.new.certificate500");
        }
        List<Tag> tags = certificate.getTag();
        int tagId;
        certificate = MappingUtils.mapToCertificateDto(certificateDao.createNewCertificate(MappingUtils.mapToCertificate(certificate)));
        int certificateId = certificate.getId();
        for (Tag tag : tags) {
            if (isUniqueTag(tag)) {
                tag = tagDao.createTag(tag);
                tagId = tag.getId();
            } else {
                String tagName = tag.getName();
                Tag existingTag = tagDao.getTagByName(tagName);
                tagId = existingTag.getId();
            }
            certificateDao.addInIntermediateTable(certificateId, tagId);
        }
        certificate.setTag(tags);
        return certificate;
    }

    private boolean isUniqueTag(Tag tag) {
        Tag existingTag = tagDao.getTagByName(tag.getName());
        return existingTag == null;
    }

    @Override
    public Certificate updateCertificateById(CertificateDto certificateDto, int certificateId) {
        Certificate certificate = certificateDao.getCertificateById(certificateId);
        if (certificate == null) {
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
        MappingUtils.setEditedFields(certificateDto, certificate);
        certificateDao.updateCertificateById(certificate, certificateId);
        return certificate;
    }

    @Override
    public void deleteCertificateById(int certificateId) {
        CertificateDto certificateDto = getCertificateById(certificateId);
        if(certificateDto != null) {
            List<Tag> tags = tagDao.getTagsByCertificateId(certificateId);
            certificateDao.deleteCertificateById(certificateId);
            for (Tag tag : tags) {
                int tagId = tag.getId();
                certificateDao.deleteFromIntermediateTableByCertificateAndTagId(certificateId, tagId);
            }
        }else{
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
    }
    @Override
    public List<CertificateDto> getPaginatedCertificates(int step) throws CertificateNotFoundException {
        if (step <= 0) {
            throw new CertificateNotFoundException("message.exception.certificate404");
        }
        List<Certificate> certificateList = certificateDao.getPaginatedCertificates(step);
        List<CertificateDto> certificatesDto = MappingUtils.mapToListCertificateDto(certificateList);
        for (CertificateDto certificate : certificatesDto) {
            List<Tag> tag = tagDao.getTagsByCertificateId(certificate.getId());
            certificate.setTag(tag);
        }
        return certificatesDto;
    }

    @Override
    public List<CertificateDto> getAllCertificatesByTagName(String tagName) {
        List<CertificateDto> certificates = null;
        Tag tag = tagDao.getTagByTagName(tagName);
        if (tag != null) {
            int tagId = tag.getId();
            certificates = MappingUtils.mapToListCertificateDto(certificateDao.getCertificatesByTagId(tagId));
            certificates.forEach((p) -> p.setTag(tagDao.getTagsByCertificateId(p.getId())));
        }
        return certificates;
    }

    @Override
    public List<CertificateDto> getCertificatesByNameOrDescription(String text) {
        List<CertificateDto> certificateDto = MappingUtils.mapToListCertificateDto(certificateDao.getCertificatesByNameOrDescription(text));
        certificateDto.forEach((p) -> p.setTag(tagDao.getTagsByCertificateId(p.getId())));
        return certificateDto;
    }

    @Override
    public List<CertificateDto> getFilteredCertificates(String name, String tag, String sort) {
        List<CertificateDto> certificateDto = null;
        if(name != null){
            certificateDto = MappingUtils.mapToListCertificateDto(certificateDao.getCertificatesByNameOrDescription(name));
        }
        if(tag!= null){
            certificateDto = getAllCertificatesByTagName(tag);
        }
        if(sort != null && (sort.equals("asc") || sort.equals("desc"))){
            certificateDto = MappingUtils.mapToListCertificateDto(certificateDao.getFilteredCertificates(sort));
        }
        if(certificateDto != null) {
            certificateDto.forEach((el) -> el.setTag(tagDao.getTagsByCertificateId(el.getId())));
        }
        return certificateDto;
    }
}

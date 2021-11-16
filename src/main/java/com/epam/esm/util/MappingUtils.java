package com.epam.esm.util;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public final class MappingUtils {

    private MappingUtils() {
    }

    public static CertificateDto mapToCertificateDto(Certificate certificate) {
        CertificateDto dto = new CertificateDto();

        dto.setId(certificate.getId());
        dto.setName(certificate.getName());
        dto.setDescription(certificate.getDescription());
        dto.setPrice(certificate.getPrice());
        dto.setDuration(certificate.getDuration());
        dto.setCreateDate(certificate.getCreateDate());
        dto.setLastUpdateDate(certificate.getLastUpdateDate());

        return dto;
    }


    public static Certificate mapToCertificate(CertificateDto dto){
        Certificate certificate = new Certificate();

        certificate.setId(dto.getId());
        certificate.setName(dto.getName());
        certificate.setDescription(dto.getDescription());
        certificate.setPrice(dto.getPrice());
        certificate.setDuration(dto.getDuration());
        certificate.setCreateDate(dto.getCreateDate());
        certificate.setLastUpdateDate(dto.getLastUpdateDate());

        return certificate;
    }

    public static List<CertificateDto> mapToListCertificateDto(List<Certificate> list){
        List<CertificateDto> certificatesDto = new ArrayList<>();
        for(Certificate certificate : list){
            certificatesDto.add(mapToCertificateDto(certificate));
        }
        return certificatesDto;
    }

    public static TagDto mapToTagDto(Tag tag){
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());

        return tagDto;
    }

    public static Tag mapToTag(TagDto tagDto){
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());

        return tag;
    }

    public static List<TagDto> mapToListTagDto(List<Tag> tags){
        List<TagDto> tagsDto = new ArrayList<>();
        tags.forEach((el) -> tagsDto.add(mapToTagDto(el)));
        return tagsDto;
    }

    public static Certificate setEditedFields(CertificateDto certificateDto, Certificate certificate){
        if(!certificate.getName().equals(certificateDto.getName()) && certificateDto.getName() != null){
            certificate.setName(certificateDto.getName());
        }
        if(!certificate.getDescription().equals(certificateDto.getDescription()) && certificateDto.getDescription() != null){
            certificate.setDescription(certificateDto.getDescription());
        }
        if(certificate.getDuration() != certificateDto.getDuration() && certificateDto.getDuration() != 0){
            certificate.setDuration(certificateDto.getDuration());
        }
        if(certificate.getPrice() != certificateDto.getPrice() && certificateDto.getPrice() != 0){
            certificate.setPrice(certificateDto.getPrice());
        }
        if(!certificate.getCreateDate().equals(certificateDto.getCreateDate()) && certificateDto.getCreateDate() != null){
            certificate.setCreateDate(certificateDto.getCreateDate());
        }
        if(!certificate.getLastUpdateDate().equals(certificateDto.getLastUpdateDate()) && certificateDto.getLastUpdateDate() != null){
            certificate.setLastUpdateDate(certificateDto.getLastUpdateDate());
        }

        return certificate;
    }

}

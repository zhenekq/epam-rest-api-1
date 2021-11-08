package com.epam.msu.util;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class ParametersUtils {

    private ParametersUtils(){}

    public static List<CertificateDto> getCertificatesWithName(List<CertificateDto> certificates, String name){
        List<CertificateDto> resultCertificates = new ArrayList<>();
        for(CertificateDto certificateDto: certificates){
            if(certificateDto.getName().contains(name) || certificateDto.getDescription().contains(name)){
                resultCertificates.add(certificateDto);
            }
        }
        return resultCertificates;
    }

    public static List<CertificateDto> getCertificatesWithTag(List<CertificateDto> certificates, String tag){
        List<CertificateDto> resultCertificates = new ArrayList<>();
        for(CertificateDto certificateDto: certificates){
            List<Tag> tags = certificateDto.getTag();
            for(Tag tag1: tags){
                if(tag1.getName().equals(tag)){
                    resultCertificates.add(certificateDto);
                    break;
                }
            }
        }
        return resultCertificates;
    }



}

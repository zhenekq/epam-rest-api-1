package com.epam.msu.service.validation;

import com.epam.msu.dto.CertificateDto;
import com.epam.msu.entity.Certificate;
import com.epam.msu.exception.CertificateNotFoundException;

public final class Validation {

    private Validation() {
    }

    public static boolean areAllFieldsExistsCertificateDto(CertificateDto dto) {
        return  dto.getName() != null && dto.getDuration() >= 0
                && dto.getDescription() != null && !(dto.getDescription().isEmpty())
                && dto.getPrice() >= 0 && dto.getCreateDate() != null
                && dto.getLastUpdateDate() != null && dto.getTag() != null
                && dto.getTag().get(0).getName() != null && !(dto.getTag().get(0).getName().isEmpty());
    }

    public static boolean areAllFieldsExistsCertificate(Certificate dto) {
        return  dto.getName() != null && dto.getDuration() >= 0
                && dto.getDescription() != null && !(dto.getDescription().isEmpty())
                && dto.getPrice() >= 0 && dto.getCreateDate() != null
                && dto.getLastUpdateDate() != null;
    }

    public static int getValidId(String id) throws CertificateNotFoundException {
        int certificateId;
        try {
            certificateId = Integer.parseInt(id);
        } catch (Exception e) {
            throw new CertificateNotFoundException("message.exception.invalid.id");
        }
        return certificateId;
    }
}

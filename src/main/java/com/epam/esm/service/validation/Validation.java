package com.epam.esm.service.validation;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;

public final class Validation {

    private Validation() {
    }

    public static boolean areAllFieldsExistsCertificateDto(CertificateDto dto) {
        return dto.getName() != null && dto.getDuration() > 0
                && dto.getDescription() != null && !(dto.getDescription().isEmpty())
                && dto.getPrice() >= 0 && dto.getCreateDate() != null
                && dto.getLastUpdateDate() != null && (dto.getTag() != null && !dto.getTag().isEmpty());
    }

    public static boolean areAllFieldsExistsCertificate(Certificate dto) {
        return dto.getName() != null && dto.getDuration() > 0
                && dto.getDescription() != null && !(dto.getDescription().isEmpty())
                && dto.getPrice() >= 0 && dto.getCreateDate() != null
                && dto.getLastUpdateDate() != null;
    }
}

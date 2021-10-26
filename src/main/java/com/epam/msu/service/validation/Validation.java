package com.epam.msu.service.validation;

import com.epam.msu.dto.CertificateDto;

public final class Validation {

    private Validation() {
    }

    public static boolean isAllFieldsExits(CertificateDto dto) {
        return  dto.getName() != null && dto.getDuration() >= 0
                && dto.getDescription() != null && !(dto.getDescription().isEmpty())
                && dto.getPrice() >= 0 && dto.getCreateDate() != null
                && dto.getLastUpdateDate() != null && dto.getTag() != null
                && dto.getTag().getName() != null && !(dto.getTag().getName().isEmpty());
    }
}

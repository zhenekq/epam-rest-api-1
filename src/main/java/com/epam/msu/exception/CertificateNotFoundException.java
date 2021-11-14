package com.epam.msu.exception;

import com.epam.msu.entity.Message;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CertificateNotFoundException extends RuntimeException {

    private final String message;
    private final Locale locale;

    public CertificateNotFoundException(String message) {this(message, LocaleContextHolder.getLocale());}

    public CertificateNotFoundException(String message, Locale locale) {
        this.locale = locale;
        this.message = message;
    }

    public String getLocalizedMessage() {
        return Message.getMessageForLocale(message, locale);
    }


}

package com.epam.esm.exception.tag;

import com.epam.esm.entity.Message;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TagNotFilledException extends RuntimeException{
    private final String message;
    private final Locale locale;

    public TagNotFilledException(String message) {
        this(message, LocaleContextHolder.getLocale());
    }

    public TagNotFilledException(String message, Locale locale) {
        this.locale = locale;
        this.message = message;
    }

    public String getLocalizedMessage() {
        return Message.getMessageForLocale(message, locale);
    }


}

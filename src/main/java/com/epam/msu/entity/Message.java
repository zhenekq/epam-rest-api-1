package com.epam.msu.entity;

import java.util.Locale;
import java.util.ResourceBundle;

public class Message {
    public static String getMessageForLocale(String messageKey, Locale locale) {
        return ResourceBundle.getBundle("message", locale)
                .getString(messageKey);
    }
}
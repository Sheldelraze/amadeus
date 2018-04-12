package com.minh.nguyen.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author Mr.Minh
 * @since 12/04/2018
 * Purpose: get message from message.properties base on message code
 */
@Component
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    private Locale locale = Locale.getDefault();

    MessageUtil() {
    }

    MessageUtil(Locale locale) {
        this.locale = locale;
    }

    //get message with no args
    public String getMessage(String msgCode) {
        return messageSource.getMessage(msgCode, null, locale);
    }

    //get message with args
    public String getMessage(String msgCode, String[] agrs) {
        return messageSource.getMessage(msgCode, agrs, locale);
    }
}
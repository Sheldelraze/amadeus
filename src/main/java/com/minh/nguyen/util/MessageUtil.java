package com.minh.nguyen.util;

import com.minh.nguyen.exception.BaseException;
import com.minh.nguyen.validator.common.BindingResult;
import com.minh.nguyen.validator.common.ErrorInfoValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author Mr.Minh
 * @since 06/01/2018
 * Purpose:
 */
@Component("MessageUtil")
public class MessageUtil {

    @Autowired
    private PropertiesUtil propUtil;

    public List<ErrorInfoValue> getMessageList(BindingResult errorList) {

        List<ErrorInfoValue> errors = new LinkedList<ErrorInfoValue>();
        if (errorList.getAllErrors().size() > 0) {
            Iterator<Throwable> ite = errorList.getAllErrors().iterator();
            Map<String, Integer> errorCheck = new HashMap<String, Integer>();
            Throwable ex = null;

            while (ite.hasNext()) {
                ex = ite.next();
                createErrorMessage(errors, ex, errorCheck);
            }
        }

        return errors;
    }

    private void createErrorMessage(List<ErrorInfoValue> errors, Throwable e,
            Map<String, Integer> errorCheck) {

        Throwable ex = e;
        while (true) {
            if (!(ex instanceof InvocationTargetException)) {
                break;
            }
            if (ex == e.getCause()) {
                break;
            }
            if (e.getCause() != null) {
                ex = e.getCause();
            }
        }

        if (ex instanceof BaseException) {
            BaseException exc = (BaseException) ex;

            String fieldName = exc.getFieldName();
            String messageId = exc.getMessageId();
            String strTmp = messageId;
            String[] params = exc.getParam();

            if (params != null && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    if (params[i] != null) {
                        strTmp += params[i];
                    }
                }
            }

            ErrorInfoValue errorValue = new ErrorInfoValue();
            if (errorValue == null || errorValue.getErrorId() == null) {
                errorValue = new ErrorInfoValue();
            }
            errorCheck.put(strTmp, 0);
            errorValue.setErrorMessage(getMessage(messageId, params));
            errorValue.setErrorId(fieldName);
            errors.add(errorValue);
        }
    }

    public String getMessage(String messageCode, String[] params) {
        params = processParams(params);
        String messageFormat = propUtil.getMsgProperty(messageCode);
        return getMessageNoCode(messageFormat, params);

    }

    private String[] processParams(String[] params) {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] == null) {
                    params[i] = "";
                }
            }
        }
        return params;
    }

    private String getMessageNoCode(String messageFormat, String[] params) {
        return new StringBuffer()
                .append(MessageFormat.format(messageFormat, (Object[]) params))
                .toString();
    }

}

package com.minh.nguyen.validator.common;

import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 13/01/2018
 * Purpose:
 */
public class ErrorInfoValue implements Serializable {

    private static final long serialVersionUID = 5552835254119417468L;

    private String errorId = null;

    private String errorMessage = null;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

}

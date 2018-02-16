package com.minh.nguyen.exception;

import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 16/02/2018
 * Purpose: for trolling purpose only, used when somebody try to do something fancy XD
 */
public class UserTryingToBeSmartException extends Exception implements Serializable {
    private static final long serialVersionUID = 437941231534L;

    public UserTryingToBeSmartException() {
    }

    public UserTryingToBeSmartException(String message) {
        this.message = message;
    }

    String message;

    public String getMessage() {
        return "java.lang.exception.UserTryingToBeSmartException: " + message;
    }
}

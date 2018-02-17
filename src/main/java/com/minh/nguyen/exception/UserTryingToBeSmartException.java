package com.minh.nguyen.exception;

import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 16/02/2018
 * Purpose: for trolling purpose only, used when somebody try to do something fancy XD
 * Which also means this class will be used only when user do something they shouldn't be doing
 * For example: modifying html, js or css then submit form and expect to achieve something mischievous,....
 */
public class UserTryingToBeSmartException extends Exception implements Serializable {
    private static final long serialVersionUID = 437941231534L;

    public UserTryingToBeSmartException() {
        this.message = "Thôi đừng bạn ơi...";
    }

    public UserTryingToBeSmartException(String message) {
        this.message = message;
    }

    String message;

    public String getMessage() {
        return "java.lang.exception.UserTryingToBeSmartException: " + message;
    }
}

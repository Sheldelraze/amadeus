package com.minh.nguyen.exception;

import java.io.Serializable;
import org.springframework.security.access.AccessDeniedException;

/**
 * @author Mr.Minh
 * @since 05/02/2018
 * Purpose:
 */
public class NoSuchPageException extends AccessDeniedException implements Serializable{
    private static final long serialVersionUID = 41231534L;

    /**
     * Constructs a <tt>CompileErrorException</tt> with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public NoSuchPageException(String message) {
        super(message);
    }
}

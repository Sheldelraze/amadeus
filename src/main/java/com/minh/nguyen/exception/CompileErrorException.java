package com.minh.nguyen.exception;


import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class CompileErrorException extends Exception implements Serializable{
    private static final long serialVersionUID = 41231534L;

    /**
     * Constructs a <tt>CompileErrorException</tt> with no specified detail
     * message.
     */
    public CompileErrorException() {}

    /**
     * Constructs a <tt>CompileErrorException</tt> with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public CompileErrorException(String message) {
        super(message);
    }
}

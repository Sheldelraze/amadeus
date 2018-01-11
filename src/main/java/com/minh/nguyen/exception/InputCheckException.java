package com.minh.nguyen.exception;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class InputCheckException extends BaseException {

    private static final long serialVersionUID = 1920098082744310558L;

    public InputCheckException(Throwable e) {
        super(e);
    }

    public InputCheckException(String code, String[] param, String errorId,
                               Throwable e) {
        super(code, param, e);
    }

    public InputCheckException(String code, String[] param, String errorId) {
        super(code, param, errorId);
    }

    public InputCheckException(String code, String[] param, Throwable e) {
        super(code, param, e);
    }

    public InputCheckException(String code, String errorId, Throwable e) {
        super(code, errorId, e);
    }

    public InputCheckException(String code, String[] param) {
        super(code, param);
    }

    public InputCheckException(String code, String errorId) {
        super(code, errorId);
    }

    public InputCheckException(String code) {
        super(code);
    }

}

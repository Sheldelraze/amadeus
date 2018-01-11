package com.minh.nguyen.exception;

/**
 * <p>ファイル名 : BaseException</p>
 * <p>説明 : BaseException</p>
 * @author bp.truong.pq
 * @since 2017/11/25
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -3130750938679514286L;

    private String messsageId = null;
    private String fieldName = null;
    private String defaultMessage = null;
    private String[] param = null;
    private boolean logOutput = false;
    public BaseException(Throwable e) {
        super(e);
    }
    public BaseException(String code, String[] param, String errorId,
                         Throwable e) {
        super(e);
        this.messsageId = code;
        this.param = param;
        this.fieldName = errorId;
    }

    public BaseException(String code, String[] param, String errorId) {
        this.messsageId = code;
        this.param = param;
        this.fieldName = errorId;
    }

    public BaseException(String code, String[] param, Throwable e) {
        this(code, param, null, e);
    }

    public BaseException(String code, String errorId, Throwable e) {
        this(code, new String[] {}, errorId, e);

    }

    public BaseException(String code, String[] param) {
        this(code, param, "");
    }

    public BaseException(String code, String errorId) {
        this(code, new String[] {}, errorId);
    }

    public BaseException(String code) {
        this(code, new String[] {});
    }

    public String getMessageId() {
        return messsageId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String errorId) {
        this.fieldName = errorId;
    }

    public String[] getParam() {
        return param;
    }

    public void setMessageId(String code) {
        this.messsageId = code;
    }

    public void setParam(String[] param) {
        this.param = param;
    }

    public boolean isLogOutput() {
        return logOutput;
    }

    public void setLogOutput(boolean logOutput) {
        this.logOutput = logOutput;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}

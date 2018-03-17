package com.minh.nguyen.vo;

/**
 * <p>ファイル名 : MessageVO</p>
 * FIXME<p>説明 : Thong tin message</p>
 * @author minh.nt
 * @since 2018/02/08
 */
public class MessageVO {

    /**
     * Type message
     */
    private String type;

    /**
     * message
     */
    private String messageId;

    /**
     * message params
     */
    private String[] params = new String[]{};

    /**
     * This constructor message vo
     * @param type      Type message
     * @param messageId Message id
     */
    public MessageVO(String type, String messageId) {
        this.type = type;
        this.messageId = messageId;
    }

    /**
     * This constructor message vo
     * @param messageId Message id
     */
    public MessageVO(String messageId) {
        this.messageId = messageId;
    }

    /**
     * This constructor message vo
     * @param messageId Message id
     * @param params    Message parameter
     */
    public MessageVO(String messageId, String[] params) {
        this.messageId = messageId;
        this.params = params;
    }

    /**
     * This constructor message vo no agrument
     */
    public MessageVO() {
    }

    /**
     * This constructor message vo
     * @param type      Type message
     * @param messageId Message id
     * @param params    Message parameter
     */
    public MessageVO(String type, String messageId, String[] params) {
        this.type = type;
        this.messageId = messageId;
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
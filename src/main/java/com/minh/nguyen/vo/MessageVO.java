package com.minh.nguyen.vo;

/**
 * @author minh.nt
 * @since 2018/02/08
 */
public class MessageVO {

    private String type;
    private String messageId;
    private String[] params = new String[]{};

    public MessageVO(String type, String messageId) {
        this.type = type;
        this.messageId = messageId;
    }

    public MessageVO() {}

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
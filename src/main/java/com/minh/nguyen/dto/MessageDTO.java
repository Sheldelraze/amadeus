package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 03/03/2018
 * Purpose:
 */
public class MessageDTO extends BaseDTO {
    private MessageType type;

    private Integer id;

    private String content;

    private String sender;

    private String comment;

    public enum MessageType {
        SUCCESS, FAIL
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}

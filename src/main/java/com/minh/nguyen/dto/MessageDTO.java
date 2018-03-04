package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 03/03/2018
 * Purpose:
 */
public class MessageDTO extends BaseDTO {
    private Integer id;

    private String content;

    private String sender;

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

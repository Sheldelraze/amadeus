package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 10/03/2018
 * Purpose:
 */
public class ConversationDTO extends BaseDTO {
    private Integer cnId;
    private Integer urId;
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getCnId() {
        return cnId;
    }

    public void setCnId(Integer cnId) {
        this.cnId = cnId;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }
}

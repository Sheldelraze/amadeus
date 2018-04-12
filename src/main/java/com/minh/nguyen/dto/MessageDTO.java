package com.minh.nguyen.dto;

import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 03/03/2018
 * Purpose:
 */
public class MessageDTO extends BaseDTO implements Serializable {

    static final long serialVersionUID = 542L;

    private MessageType type;

    private Integer id;

    private String topic;

    private String content;

    private String urId;

    private String comment;

    private String username;

    private String avatar;

    private String sendTime;

    private Integer limitFrom;

    private Integer limitTo;

    private Integer isRead;

    private UserDTO sender;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getLimitFrom() {
        return limitFrom;
    }

    public void setLimitFrom(Integer limitFrom) {
        this.limitFrom = limitFrom;
    }

    public Integer getLimitTo() {
        return limitTo;
    }

    public void setLimitTo(Integer limitTo) {
        this.limitTo = limitTo;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum MessageType {
        SUCCESS,
        FAIL,
        WARN,
        ERROR,
        INFO
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrId() {
        return urId;
    }

    public void setUrId(String urId) {
        this.urId = urId;
    }
}

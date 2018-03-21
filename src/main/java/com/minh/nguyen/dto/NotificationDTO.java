package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 21/03/2018
 * Purpose:
 */
public class NotificationDTO extends BaseDTO {
    private Integer id;
    private Integer urId;
    private Integer type;
    private Integer isRead;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

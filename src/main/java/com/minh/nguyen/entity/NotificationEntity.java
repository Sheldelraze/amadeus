package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 21/03/2018
 * Purpose:
 */
@Table(name = "notification")
public class NotificationEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 64341290070073L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "urId")
    private Integer urId;

    @Column(name = "type")
    private Integer type;

    @Column(name = "isRead")
    private Integer isRead;

    @Column(name = "content")
    private String content;

    @Column(name = "link")
    private String link;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

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

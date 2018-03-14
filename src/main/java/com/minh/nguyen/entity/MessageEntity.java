package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name="message")
public class MessageEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 6434129042143L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "urId")
    private Integer urId;

    @Column(name = "cnId")
    private Integer cnId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public Integer getCnId() {
        return cnId;
    }

    public void setCnId(Integer cnId) {
        this.cnId = cnId;
    }
}

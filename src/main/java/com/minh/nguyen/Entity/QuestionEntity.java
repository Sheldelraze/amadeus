package com.minh.nguyen.Entity;

import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Table(name="question")
public class QuestionEntity extends BaseEntity implements Serializable{
    static final long serialVersionUID = 44441533L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "timePosted")
    private LocalDateTime timePosted;

    @Column(name = "isAnswered")
    private Integer isAnswered;

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

    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(LocalDateTime timePosted) {
        this.timePosted = timePosted;
    }

    public Integer getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(Integer isAnswered) {
        this.isAnswered = isAnswered;
    }
}

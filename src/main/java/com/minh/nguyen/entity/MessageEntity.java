package com.minh.nguyen.entity;

import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name="message")
public class MessageEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 642143L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "sentTime")
    private LocalDateTime sentTime;


}

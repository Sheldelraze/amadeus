package com.minh.nguyen.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "lecturer")
public class LecturerEntity extends UserEntity implements Serializable {
    static final long serialVersionUID = 1462L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name="userId")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    @Column(name="degree")
    private String degree;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

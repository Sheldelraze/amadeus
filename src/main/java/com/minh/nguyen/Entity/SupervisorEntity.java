package com.minh.nguyen.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "supervisor")
public class SupervisorEntity extends UserEntity implements Serializable {
    static final long serialVersionUID = 1242666L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name="userId")
    private Integer userId;

    @Column(name="degree")
    private String degree;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

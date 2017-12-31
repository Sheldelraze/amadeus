package com.minh.nguyen.Entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "admin")
public class AdminEntity extends UserEntity implements Serializable{
    static final long serialVersionUID = 45L;
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

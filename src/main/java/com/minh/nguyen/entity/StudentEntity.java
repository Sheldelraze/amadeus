package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "student")
public class StudentEntity extends  UserEntity implements Serializable {
    static final long serialVersionUID = 1111142L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "probSolveCnt")
    private Integer probSolveCnt;

    @Override
    public Integer getId() {
        return id;
    }

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

    public Integer getProbSolveCnt() {
        return probSolveCnt;
    }

    public void setProbSolveCnt(Integer probSolveCnt) {
        this.probSolveCnt = probSolveCnt;
    }
}

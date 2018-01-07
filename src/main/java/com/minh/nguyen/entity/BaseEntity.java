package com.minh.nguyen.entity;


import org.joda.time.LocalDateTime;

import javax.persistence.Column;


/**
 *
 */
public class BaseEntity {
    @Column(name = "createTime")
    private LocalDateTime createTime;

    @Column(name = "createUser")
    private String createUser;

    @Column(name = "createClass")
    private String createClass;

    @Column(name = "updateTime")
    private LocalDateTime updateTime;

    @Column(name = "updateUser")
    private String updateUser;

    @Column(name = "updateClass")
    private String updateClass;

    @Column(name = "deleteFlg")
    private String deleteFlg;

    @Column(name = "deleteTime")
    private String deleteTime;



    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateClass() {
        return createClass;
    }

    public void setCreateClass(String createClass) {
        this.createClass = createClass;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateClass() {
        return updateClass;
    }

    public void setUpdateClass(String updateClass) {
        this.updateClass = updateClass;
    }

    public String getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(String deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }
}

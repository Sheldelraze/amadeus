package com.minh.nguyen.dto;

import java.util.Date;

public class BaseDTO {
    private Date createTime;

    private String createUser;

    private String createClass;

    private Date updateTime;

    private String updateUser;

    private String updateClass;

    private String deleteFlg;

    private String deleteTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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

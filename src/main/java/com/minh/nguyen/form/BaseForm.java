package com.minh.nguyen.form;

import com.minh.nguyen.validator.common.ErrorInfoValue;

import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class BaseForm {

    private String screenMessage;


    private Date createTime;


    private String createUser;


    private String createClass;

    private Date updateTime;

    private String updateUser;

    private String updateClass;

    private String screenName;

    private List<ErrorInfoValue> error;

    public List<ErrorInfoValue> getError() {
        return error;
    }

    public void setError(List<ErrorInfoValue> error) {
        this.error = error;
    }

    public String getScreenMessage() {
        return screenMessage;
    }

    public void setScreenMessage(String screenMessage) {
        this.screenMessage = screenMessage;
    }

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

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

}

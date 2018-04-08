package com.minh.nguyen.form.user;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Number;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 08/04/2018
 * Purpose:
 */
public class UserCreateForm extends BaseForm {

    @Required(displayFieldName = "handle")
    @MaxLength(displayFieldName = "handle", maxlength = 30)
    private String handle;

    @Required(displayFieldName = "password")
    @MaxLength(displayFieldName = "password", maxlength = 30)
    private String password;

    @MaxLength(displayFieldName = "password", maxlength = 40)
    private String fullname;

    @Required(displayFieldName = "reId")
    @Number(minValue = 1,maxValue = 4,displayFieldName = "reId")
    private Integer reId;
    private Integer[] lstAuyId;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }

    public Integer[] getLstAuyId() {
        return lstAuyId;
    }

    public void setLstAuyId(Integer[] lstAuyId) {
        this.lstAuyId = lstAuyId;
    }
}

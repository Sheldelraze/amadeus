package com.minh.nguyen.form.user;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Number;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 09/04/2018
 * Purpose:
 */
public class UserUpdateForm extends BaseForm {

    private Integer id;

    private String handle;

    @MaxLength(displayFieldName = "password", maxlength = 30)
    private String password;

    @MaxLength(displayFieldName = "fullnamen", maxlength = 40)
    @Required
    private String fullname;

    @Required(displayFieldName = "reId")
    @Number(minValue = 1,maxValue = 4,displayFieldName = "reId")
    private String reId;

    private String[] lstAuyId;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String[] getLstAuyId() {
        return lstAuyId;
    }

    public void setLstAuyId(String[] lstAuyId) {
        this.lstAuyId = lstAuyId;
    }
}

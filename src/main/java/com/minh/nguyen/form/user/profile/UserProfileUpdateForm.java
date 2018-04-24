package com.minh.nguyen.form.user.profile;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.Format;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 24/04/2018
 * Purpose:
 */
public class UserProfileUpdateForm extends BaseForm {
    private Integer urId;
    private String handle;

    @Required(displayFieldName = "fullname")
    @MaxLength(displayFieldName = "fullname",maxlength = 45)
    private String fullname;

    @Format(type = Format.FormatType.DATE,displayFieldName = "date",pattern = "dd/MM/yyyy")
    private String dateOfBirth;

    @Format(type= Format.FormatType.EMAIL)
    private String email;

    @Format(type= Format.FormatType.PHONE)
    private String phone;

    @MaxLength(displayFieldName = "fullname",maxlength = 2000)
    private String description;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }
}

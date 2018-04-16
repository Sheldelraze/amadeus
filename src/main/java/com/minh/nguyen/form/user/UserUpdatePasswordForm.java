package com.minh.nguyen.form.user;

/**
 * @author Mr.Minh
 * @since 16/04/2018
 * Purpose:
 */
public class UserUpdatePasswordForm {
    private Integer urId;
    private String oldPassword;
    private String newPassword;

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

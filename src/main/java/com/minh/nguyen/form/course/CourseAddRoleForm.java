package com.minh.nguyen.form.course;

/**
 * @author Mr.Minh
 * @since 17/02/2018
 * Purpose:
 */
public class CourseAddRoleForm extends CourseLayoutForm {
    private String fullname;
    private String reId;
    private String[] lstUrId;
    private String auyId;

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

    public String[] getLstUrId() {
        return lstUrId;
    }

    public void setLstUrId(String[] lstUrId) {
        this.lstUrId = lstUrId;
    }

    public String getAuyId() {
        return auyId;
    }

    public void setAuyId(String auyId) {
        this.auyId = auyId;
    }
}

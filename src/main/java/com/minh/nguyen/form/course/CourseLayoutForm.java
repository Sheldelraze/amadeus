package com.minh.nguyen.form.course;

import com.minh.nguyen.form.BaseForm;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class CourseLayoutForm extends BaseForm {
    protected Integer id;
    private Integer viewTab;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewTab() {
        return viewTab;
    }

    public void setViewTab(Integer viewTab) {
        this.viewTab = viewTab;
    }
}

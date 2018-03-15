package com.minh.nguyen.form.course;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.annotation.Format;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Number;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 21/01/2018
 * Purpose:
 */
public class CourseCreateForm extends BaseForm {
    @Required(displayFieldName = "name")
    @MaxLength(displayFieldName = "name", maxlength = 50)
    private String name;

    @Required(displayFieldName = "date")
    @Format(type = Format.FormatType.DATE,displayFieldName = "date",pattern = "MM/dd/yyyy")
    private String startTime;

    @Required(displayFieldName = "time")
    @Format(type = Format.FormatType.DATE,displayFieldName = "date",pattern = "MM/dd/yyyy")
    private String endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}

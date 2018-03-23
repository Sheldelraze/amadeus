package com.minh.nguyen.form.course;

import com.minh.nguyen.validator.annotation.Format;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class CourseSettingForm  extends CourseLayoutForm{
    @Required(displayFieldName = "name")
    @MaxLength(displayFieldName = "name", maxlength = 50)
    private String name;

    @Required(displayFieldName = "startTime")
    @Format(type = Format.FormatType.DATE,displayFieldName = "startTime",pattern = "MM/dd/yyyy")
    private String startTime;

    @Required(displayFieldName = "endTime")
    @Format(type = Format.FormatType.DATE,displayFieldName = "endTime",pattern = "MM/dd/yyyy")
    private String endTime;

    private Integer showSubmit;

    private String description;

    private String requirement;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

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

    public Integer getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(Integer showSubmit) {
        this.showSubmit = showSubmit;
    }
}

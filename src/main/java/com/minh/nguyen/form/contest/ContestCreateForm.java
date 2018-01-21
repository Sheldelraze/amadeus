package com.minh.nguyen.form.contest;

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
public class ContestCreateForm extends BaseForm {
    @Required(displayFieldName = "name")
    @MaxLength(displayFieldName = "name", maxlength = 50)
    private String name;

    @Required(displayFieldName = "date")
    @Format(type = Format.FormatType.DATE,displayFieldName = "date",pattern = "MM/dd/yyyy")
    private String date;

    @Required(displayFieldName = "time")
    @Format(type = Format.FormatType.TIME,displayFieldName = "time",pattern = "HH:mm")
    private String time;

    @Required(displayFieldName = "duration")
    @Number(minValue=10,displayFieldName = "duration")
    private String duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

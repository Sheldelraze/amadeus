package com.minh.nguyen.form.course;

import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 19/02/2018
 * Purpose:
 */
public class CourseAnswerForm extends CourseLayoutForm {
    private String atId;

    @Required(displayFieldName = "answer")
    @MaxLength(maxlength = 2000, displayFieldName = "answer")
    private String answer;

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

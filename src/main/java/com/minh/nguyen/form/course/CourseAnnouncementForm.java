package com.minh.nguyen.form.course;

import com.minh.nguyen.validator.annotation.MaxLength;

/**
 * @author Mr.Minh
 * @since 18/02/2018
 * Purpose:
 */
public class CourseAnnouncementForm extends CourseLayoutForm {
    private String pmId;

    @MaxLength(maxlength = 2000, displayFieldName = "question")
    private String question;

    @MaxLength(maxlength = 2000, displayFieldName = "answer")
    private String answer;

    public String getPmId() {
        return pmId;
    }

    public void setPmId(String pmId) {
        this.pmId = pmId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

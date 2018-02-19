package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 18/02/2018
 * Purpose:
 */
public class AnnouncementDTO extends BaseDTO {
    private Integer id;

    private ProblemDTO problem;

    private String question;

    private String answer;

    private String timePosted;

    private Integer isHidden;

    private Integer isAnswered;

    private Integer isFromCreator;

    public Integer getIsFromCreator() {
        return isFromCreator;
    }

    public void setIsFromCreator(Integer isFromCreator) {
        this.isFromCreator = isFromCreator;
    }

    public Integer getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(Integer isAnswered) {
        this.isAnswered = isAnswered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProblemDTO getProblem() {
        return problem;
    }

    public void setProblem(ProblemDTO problem) {
        this.problem = problem;
    }

    public String getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(String timePosted) {
        this.timePosted = timePosted;
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

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }
}

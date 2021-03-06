package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "announcement")
public class AnnouncementEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 424412L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmId")
    private Integer pmId;

    @Column(name="question")
    private String question;

    @Column(name="answer")
    private String answer;

    @Column(name="isHidden")
    private Integer isHidden;

    @Column(name = "isAnswered")
    private Integer isAnswered;

    @Column(name = "isFromCreator")
    private Integer isFromCreator;

    public Integer getIsFromCreator() {
        return isFromCreator;
    }

    public void setIsFromCreator(Integer isFromCreator) {
        this.isFromCreator = isFromCreator;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Integer getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(Integer isAnswered) {
        this.isAnswered = isAnswered;
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

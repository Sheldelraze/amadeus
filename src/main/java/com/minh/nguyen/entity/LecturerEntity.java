package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "lecturer")
public class LecturerEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 1462L;

    @Id
    @Column(name="urId")
    private Integer urId;

    @Column(name="degree")
    private String degree;

    @Column(name="education")
    private String education;

    @Column(name="career")
    private String career;

    @Column(name="research")
    private String research;

    @Column(name="publication")
    private String publication;

    @Column(name="award")
    private String award;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

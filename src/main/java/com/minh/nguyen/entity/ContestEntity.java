package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "contest")
public class ContestEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 542L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description")
    private String description;

    @Column(name = "requirement")
    private String requirement;

    @Column(name = "prize")
    private String prize;

    @Column(name = "showTest")
    private Integer showTest;

    @Column(name = "showSubmit")
    private Integer showSubmit;

    @Column(name = "judgeType")
    private Integer judgeType;

    @Column(name = "showStatus")
    private Integer showStatus;

    @Column(name = "canPractice")
    private Integer canPractice;

    @Column(name = "isPublic")
    private Integer isPublic;

    @Column(name = "showToAll")
    private Integer showToAll;

    public Integer getIsPublic() {
        return isPublic;
    }

    public Integer getShowToAll() {
        return showToAll;
    }

    public Integer getShowInforToAll() {
        return showToAll;
    }

    public void setShowToAll(Integer showToAll) {
        this.showToAll = showToAll;
    }

    public Integer getIsAnyoneCanParticipate() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(Integer judgeType) {
        this.judgeType = judgeType;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getCanPractice() {
        return canPractice;
    }

    public void setCanPractice(Integer canPractice) {
        this.canPractice = canPractice;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

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

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Integer getShowTest() {
        return showTest;
    }

    public void setShowTest(Integer showTest) {
        this.showTest = showTest;
    }

    public Integer getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(Integer showSubmit) {
        this.showSubmit = showSubmit;
    }
}

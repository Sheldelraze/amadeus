package com.minh.nguyen.vo.contest;

/**
 * @author Mr.Minh
 * @since 01/02/2018
 * Purpose:
 */
public class ContestSettingVO {
    private Integer id;
    private String name;
    private Integer duration;
    private String date;
    private String time;
    private String startTime;
    private String description;
    private String prize;
    private String requirement;
    private Integer isPublished;
    private Integer showTest;
    private Integer showSubmit;
    private Integer judgeType;
    private Integer showStatus;
    private Integer canPractice;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Integer getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Integer isPublished) {
        this.isPublished = isPublished;
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
}

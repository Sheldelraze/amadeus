package com.minh.nguyen.form.contest;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class ContestSettingForm  extends  ContestLayoutForm{
    private Integer id;
    private String name;
    private String duration;
    private String startTime;
    private String description;
    private String prize;
    private String requirement;
    private String isPublished;
    private String showTest;
    private String showSubmit;
    private String judgeType;
    private String showStatus;
    private String canPractice;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(String isPublished) {
        this.isPublished = isPublished;
    }

    public String getShowTest() {
        return showTest;
    }

    public void setShowTest(String showTest) {
        this.showTest = showTest;
    }

    public String getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(String showSubmit) {
        this.showSubmit = showSubmit;
    }

    public String getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(String judgeType) {
        this.judgeType = judgeType;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public String getCanPractice() {
        return canPractice;
    }

    public void setCanPractice(String canPractice) {
        this.canPractice = canPractice;
    }
}

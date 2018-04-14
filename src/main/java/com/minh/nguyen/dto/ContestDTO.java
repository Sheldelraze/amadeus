package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 21/01/2018
 * Purpose:
 */
public class ContestDTO extends BaseDTO {
    private Integer id;
    private String name;
    private String date;
    private String time;
    private Integer duration;
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
    private Integer isPublic;
    private Integer showToAll;
    private String endTime;
    private Integer doUpdateCountDown;
    private String timerMessage;
    private UserDTO creator;
    private Integer userCnt;
    private Boolean isOngoing;
    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public Integer getUserCnt() {
        return userCnt;
    }

    public void setUserCnt(Integer userCnt) {
        this.userCnt = userCnt;
    }

    public Boolean getOngoing() {
        return isOngoing;
    }

    public void setOngoing(Boolean ongoing) {
        isOngoing = ongoing;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getTimerMessage() {
        return timerMessage;
    }

    public void setTimerMessage(String timerMessage) {
        this.timerMessage = timerMessage;
    }

    public Integer getDoUpdateCountDown() {
        return doUpdateCountDown;
    }

    public void setDoUpdateCountDown(Integer doUpdateCountDown) {
        this.doUpdateCountDown = doUpdateCountDown;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getShowToAll() {
        return showToAll;
    }

    public void setShowToAll(Integer showToAll) {
        this.showToAll = showToAll;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getDuration() {
        return duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}

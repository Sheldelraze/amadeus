package com.minh.nguyen.form.course;
import com.minh.nguyen.validator.annotation.Format;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Number;
import com.minh.nguyen.validator.annotation.Required;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
public class CourseSettingForm  extends CourseLayoutForm{
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
    private String isPublic;
    private String showToAll;

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

    public String getShowToAll() {
        return showToAll;
    }

    public void setShowToAll(String showToAll) {
        this.showToAll = showToAll;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
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

package com.minh.nguyen.dto;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 27/01/2018
 * Purpose:
 */
public class SubmissionDTO extends BaseDTO {
    private Integer id;
    private Integer pmId;
    private String pmNm;
    private String handle;
    private Integer timeRun;
    private Integer memoryUsed;
    private String sourceCode;
    private String verdict;
    private Integer judgeStatus;
    private String submitTime;
    private String leNm;
    private List<SubmitDetailDTO> lstSubmitDetail;
    private ContestDTO contestDTO;
    private CourseDTO courseDTO;
    private UserDTO user;
    private Integer correctAns;

    public Integer getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(Integer correctAns) {
        this.correctAns = correctAns;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public ContestDTO getContestDTO() {
        return contestDTO;
    }

    public void setContestDTO(ContestDTO contestDTO) {
        this.contestDTO = contestDTO;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public List<SubmitDetailDTO> getLstSubmitDetail() {
        return lstSubmitDetail;
    }

    public void setLstSubmitDetail(List<SubmitDetailDTO> lstSubmitDetail) {
        this.lstSubmitDetail = lstSubmitDetail;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getPmNm() {
        return pmNm;
    }

    public void setPmNm(String pmNm) {
        this.pmNm = pmNm;
    }

    public String getLeNm() {
        return leNm;
    }

    public void setLeNm(String leNm) {
        this.leNm = leNm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeRun() {
        return timeRun;
    }

    public void setTimeRun(Integer timeRun) {
        this.timeRun = timeRun;
    }

    public Integer getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Integer memoryUsed) {
        this.memoryUsed = memoryUsed;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Integer getJudgeStatus() {
        return judgeStatus;
    }

    public void setJudgeStatus(Integer judgeStatus) {
        this.judgeStatus = judgeStatus;
    }
}

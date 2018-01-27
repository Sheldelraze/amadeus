package com.minh.nguyen.dto;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 27/01/2018
 * Purpose:
 */
public class SubmissionDTO extends BaseDTO {
    private Integer id;
    private String pmNm;
    private Integer timeRun;
    private Integer memoryUsed;
    private String verdict;
    private Integer judgeStatus;
    private String submitTime;
    private String leNm;

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

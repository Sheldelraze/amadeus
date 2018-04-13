package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Table(name="submission")
public class SubmissionEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 5443241533L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "leId")
    private Integer leId;

    @Column(name = "pmId")
    private Integer pmId;

    @Column(name = "urId")
    private Integer urId;

    @Column(name = "timeRun")
    private Integer timeRun;

    @Column(name = "memoryUsed")
    private Integer memoryUsed;

    @Column(name = "sourceCode")
    private String sourceCode;

    @Column(name = "judgeStatus")
    private Integer judgeStatus;

    @Column(name = "verdict")
    private String verdict;

    @Column(name = "isPublic")
    private Integer isPublic;

    @Column(name = "point")
    private Integer point;

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
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

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
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

    public Integer getLeId() {
        return leId;
    }

    public void setLeId(Integer leId) {
        this.leId = leId;
    }


    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer getJudgeStatus() {
        return judgeStatus;
    }

    public void setJudgeStatus(Integer judgeStatus) {
        this.judgeStatus = judgeStatus;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
}

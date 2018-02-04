package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
@Table(name="ct_pm")
public class CtPmEntity extends BaseEntity implements Serializable{
    @Id
    @Column(name = "ctId")
    private Integer ctId;

    @Id
    @Column(name = "pmId")
    private Integer pmId;

    @Column(name = "solveCnt")
    private Integer solveCnt;

    @Column(name = "firstSolve")
    private Date firstSolve;

    @Column(name = "totalSubmission")
    private Integer totalSubmission;

    @Column(name = "isHidden")
    private Integer isHidden;

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }

    public Integer getSolveCnt() {
        return solveCnt;
    }

    public void setSolveCnt(Integer solveCnt) {
        this.solveCnt = solveCnt;
    }

    public Date getFirstSolve() {
        return firstSolve;
    }

    public void setFirstSolve(Date firstSolve) {
        this.firstSolve = firstSolve;
    }

    public Integer getTotalSubmission() {
        return totalSubmission;
    }

    public void setTotalSubmission(Integer totalSubmission) {
        this.totalSubmission = totalSubmission;
    }
}

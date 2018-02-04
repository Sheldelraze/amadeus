package com.minh.nguyen.dto;

import java.util.Date;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
public class CtPmDTO extends BaseDTO{
    private Integer ctId;

    private Integer pmId;

    private Integer solveCnt;

    private Date firstSolve;

    private Integer totalSubmission;

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

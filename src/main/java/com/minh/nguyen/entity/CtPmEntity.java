package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
@Table(name="ct_pm")
public class CtPmEntity extends BaseEntity implements Serializable{

    static final long serialVersionUID = 444443252634L;

    @Id
    @Column(name = "ctId")
    private Integer ctId;

    @Id
    @Column(name = "pmId")
    private Integer pmId;

    @Column(name = "isHidden")
    private Integer isHidden;

    @Column(name = "solveCnt")
    private Integer solveCnt;

    @Column(name = "firstSolve")
    private Date firstSolve;

    @Column(name = "totalSubmission")
    private Integer totalSubmission;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
}

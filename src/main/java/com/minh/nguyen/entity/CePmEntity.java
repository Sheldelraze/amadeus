package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Minh
 * @since 22/03/2018
 * Purpose:
 */
@Table(name = "ce_pm")
public class CePmEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 12343252634L;

    @Id
    @Column(name = "ceId")
    private Integer ceId;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }
}

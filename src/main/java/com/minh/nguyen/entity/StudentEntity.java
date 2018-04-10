package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "student")
public class StudentEntity extends  BaseEntity implements Serializable {
    static final long serialVersionUID = 1111142L;

    @Id
    @Column(name = "urId")
    private Integer urId;

    @Column(name = "csId")
    private Integer csId;

    @Column(name = "point")
    private Integer point;

    @Column(name = "solveCnt")
    private Integer solveCnt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getSolveCnt() {
        return solveCnt;
    }

    public void setSolveCnt(Integer solveCnt) {
        this.solveCnt = solveCnt;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }
}

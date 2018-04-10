package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 11/04/2018
 * Purpose:
 */
public class StudentDTO  extends UserDTO{
    private Integer solveCnt;
    private Integer point;
    private Integer csId;

    public Integer getSolveCnt() {
        return solveCnt;
    }

    public void setSolveCnt(Integer solveCnt) {
        this.solveCnt = solveCnt;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }
}

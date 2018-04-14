package com.minh.nguyen.dto;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 11/04/2018
 * Purpose:
 */
public class StudentDTO  extends UserDTO{
    private Integer solveCnt;
    private Integer point;
    private Integer csId;
    private ClassDTO classDTO;
    private List<SubmissionDTO> lstSolved;
    private List<ContestDTO> lstContest;

    public ClassDTO getClassDTO() {
        return classDTO;
    }

    public void setClassDTO(ClassDTO classDTO) {
        this.classDTO = classDTO;
    }

    public List<SubmissionDTO> getLstSolved() {
        return lstSolved;
    }

    public void setLstSolved(List<SubmissionDTO> lstSolved) {
        this.lstSolved = lstSolved;
    }

    public List<ContestDTO> getLstContest() {
        return lstContest;
    }

    public void setLstContest(List<ContestDTO> lstContest) {
        this.lstContest = lstContest;
    }

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

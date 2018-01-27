package com.minh.nguyen.vo;

import com.minh.nguyen.dto.SubmissionDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 27/01/2018
 * Purpose:
 */
public class StatusVO {
    List<SubmissionDTO> lstSubmission;

    public List<SubmissionDTO> getLstSubmission() {
        return lstSubmission;
    }

    public void setLstSubmission(List<SubmissionDTO> lstSubmission) {
        this.lstSubmission = lstSubmission;
    }
}

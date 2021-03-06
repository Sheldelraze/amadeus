package com.minh.nguyen.form.contest;

import com.minh.nguyen.dto.ProblemDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 03/02/2018
 * Purpose:
 */
public class ContestAddProblemForm extends ContestLayoutForm {
    private List<ProblemDTO> lstProblemDTO;
    private String[] lstPmId;

    public List<ProblemDTO> getLstProblemDTO() {
        return lstProblemDTO;
    }

    public void setLstProblemDTO(List<ProblemDTO> lstProblemDTO) {
        this.lstProblemDTO = lstProblemDTO;
    }

    public String[] getLstPmId() {
        return lstPmId;
    }

    public void setLstPmId(String[] lstPmId) {
        this.lstPmId = lstPmId;
    }
}

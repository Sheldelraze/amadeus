package com.minh.nguyen.vo.contest;

import com.minh.nguyen.dto.ProblemDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
public class ContestProblemVO{
    List<ProblemDTO> lstProblemDTO;

    public List<ProblemDTO> getLstProblemDTO() {
        return lstProblemDTO;
    }

    public void setLstProblemDTO(List<ProblemDTO> lstProblemDTO) {
        this.lstProblemDTO = lstProblemDTO;
    }
}

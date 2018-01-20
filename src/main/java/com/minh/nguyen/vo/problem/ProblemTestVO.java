package com.minh.nguyen.vo.problem;

import com.minh.nguyen.dto.InputDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 14/01/2018
 * Purpose:
 */
public class ProblemTestVO extends  ProblemLayoutVO {
    List<InputDTO> lstInput;

    public List<InputDTO> getLstInput() {
        return lstInput;
    }

    public void setLstInput(List<InputDTO> lstInput) {
        this.lstInput = lstInput;
    }
}

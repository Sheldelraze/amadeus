package com.minh.nguyen.vo.contest;

import com.minh.nguyen.dto.ContestDTO;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 25/02/2018
 * Purpose:
 */
public class ContestListVO {
    public List<ContestDTO> ongoingLst;
    public List<ContestDTO> pastLst;

    public List<ContestDTO> getOngoingLst() {
        return ongoingLst;
    }

    public void setOngoingLst(List<ContestDTO> ongoingLst) {
        this.ongoingLst = ongoingLst;
    }

    public List<ContestDTO> getPastLst() {
        return pastLst;
    }

    public void setPastLst(List<ContestDTO> pastLst) {
        this.pastLst = pastLst;
    }
}

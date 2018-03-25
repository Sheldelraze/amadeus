package com.minh.nguyen.vo.course;

import com.minh.nguyen.dto.CourseDTO;
import com.minh.nguyen.vo.BaseVO;

import java.util.List;

public class CourseListVO extends BaseVO {
    private List<CourseDTO> ongoingLst;
    private List<CourseDTO> pastLst;

    public List<CourseDTO> getOngoingLst() {
        return ongoingLst;
    }

    public void setOngoingLst(List<CourseDTO> ongoingLst) {
        this.ongoingLst = ongoingLst;
    }

    public List<CourseDTO> getPastLst() {
        return pastLst;
    }

    public void setPastLst(List<CourseDTO> pastLst) {
        this.pastLst = pastLst;
    }
}

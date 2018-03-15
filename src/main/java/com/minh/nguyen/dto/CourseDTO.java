package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 14/03/2018
 * Purpose:
 */
public class CourseDTO extends BaseDTO {
    private String start;

    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

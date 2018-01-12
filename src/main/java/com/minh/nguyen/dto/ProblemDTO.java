package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
public class ProblemDTO extends BaseDTO {
    private int id;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

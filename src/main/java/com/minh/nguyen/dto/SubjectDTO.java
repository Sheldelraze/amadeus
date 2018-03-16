package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
public class SubjectDTO extends BaseDTO {
    private Integer id;

    private String name;

    private Integer credit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}

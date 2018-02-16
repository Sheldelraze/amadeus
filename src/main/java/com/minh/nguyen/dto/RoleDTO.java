package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
public class RoleDTO extends BaseDTO{
    private Integer id;
    private String name;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
}

package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
public class AuthorityDTO extends BaseDTO {
    private Integer id;
    private String name;

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

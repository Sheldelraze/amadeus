package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
public class AuthorityDTO extends BaseDTO {
    private Integer id;
    private String name;
    private Boolean isCheck;

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public AuthorityDTO() {
    }

    public AuthorityDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
        isCheck = false;
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

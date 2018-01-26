package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 26/01/2018
 * Purpose:
 */
public class LanguageDTO {
    private Integer id;

    private String name;

    private String extension;

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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}

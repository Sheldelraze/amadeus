package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 07/04/2018
 * Purpose:
 */
public class LevelDTO extends BaseDTO {
     private Integer id;
     private String name;
     private String color;
    private Integer point;

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 21/03/2018
 * Purpose:
 */
public class ClassDTO extends BaseDTO {
    private Integer id;
    private String name;
    private Integer intake;
    private String major;
    private String academicYear;

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
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

    public Integer getIntake() {
        return intake;
    }

    public void setIntake(Integer intake) {
        this.intake = intake;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

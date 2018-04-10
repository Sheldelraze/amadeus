package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "lecturer")
public class LecturerEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 1462L;

    @Id
    @Column(name="urId")
    private Integer urId;

    @Column(name="degree")
    private String degree;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}

package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 11/02/2018
 * Purpose:
 */
@Table(name="ct_ur_sn")
public class CtUrSnEntity extends BaseEntity implements Serializable {

    static final long serialVersionUID = 4432523333187634L;

    @Id
    @Column(name = "ctId")
    private Integer ctId;

    @Id
    @Column(name = "urId")
    private Integer urId;

    @Id
    @Column(name = "snId")
    private Integer snId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public Integer getSnId() {
        return snId;
    }

    public void setSnId(Integer snId) {
        this.snId = snId;
    }
}

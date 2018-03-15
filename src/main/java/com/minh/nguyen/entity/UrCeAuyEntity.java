package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 15/03/2018
 * Purpose:
 */
@Table(name = "ur_ce_auy")
public class UrCeAuyEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 93430009L;
    @Id
    @Column(name = "ceId")
    private Integer ceId;

    @Id
    @Column(name = "urId")
    private Integer urId;

    @Id
    @Column(name = "auyId")
    private Integer auyId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public Integer getAuyId() {
        return auyId;
    }

    public void setAuyId(Integer auyId) {
        this.auyId = auyId;
    }
}

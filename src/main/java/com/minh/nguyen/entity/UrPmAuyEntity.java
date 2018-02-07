package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 07/02/2018
 * Purpose:
 */
@Table(name = "ur_pm_auy")
public class UrPmAuyEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 33339343L;

    @Id
    @Column(name = "pmId")
    private Integer pmId;

    @Id
    @Column(name = "urId")
    private Integer urId;

    @Id
    @Column(name = "auyId")
    private Integer auyId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
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

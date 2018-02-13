package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
@Table(name="ct_pm")
public class CtPmEntity extends BaseEntity implements Serializable{
    @Id
    @Column(name = "ctId")
    private Integer ctId;

    @Id
    @Column(name = "pmId")
    private Integer pmId;

    @Column(name = "isHidden")
    private Integer isHidden;

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }
}

package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 22/03/2018
 * Purpose:
 */
@Table(name = "ce_pm")
public class CePmEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 12343252634L;

    @Id
    @Column(name = "ceId")
    private Integer ceId;

    @Id
    @Column(name = "pmId")
    private Integer pmId;

    @Column(name = "isHidden")
    private Integer isHidden;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getPmId() {
        return pmId;
    }

    public void setPmId(Integer pmId) {
        this.pmId = pmId;
    }
}

package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
@Table(name="ct_pm")
public class CtPmEntity extends BaseEntity implements Serializable{

    static final long serialVersionUID = 444443252634L;

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

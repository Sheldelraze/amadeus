package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 19/03/2018
 * Purpose:
 */
@Table(name = "ce_ml")
public class CeMlEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 4430073252634L;

    @Id
    @Column(name = "ceId")
    private Integer ceId;

    @Id
    @Column(name = "mlId")
    private Integer mlId;

    @Column(name = "isHidden")
    private Integer isHidden;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getMlId() {
        return mlId;
    }

    public void setMlId(Integer mlId) {
        this.mlId = mlId;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }
}

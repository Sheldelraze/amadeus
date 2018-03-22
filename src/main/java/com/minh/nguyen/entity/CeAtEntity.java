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
@Table(name = "ce_at")
public class CeAtEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 12765876634L;

    @Id
    @Column(name = "ceId")
    private Integer ceId;

    @Id
    @Column(name = "atId")
    private Integer atId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
    }
}

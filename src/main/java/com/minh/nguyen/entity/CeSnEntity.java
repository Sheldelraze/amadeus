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
@Table(name = "ce_sn")
public class CeSnEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 12343666252634L;

    @Id
    @Column(name = "ceId")
    private Integer ceId;

    @Id
    @Column(name = "snId")
    private Integer snId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getSnId() {
        return snId;
    }

    public void setSnId(Integer snId) {
        this.snId = snId;
    }
}

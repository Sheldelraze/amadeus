package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mr.Minh
 * @since 26/01/2018
 * Purpose:
 */
@Table(name = "sn_sDl")
public class SnSDlEntity {
    static final long serialVersionUID = 6421133943L;
    @Id
    @Column(name = "snId")
    private int snId;

    @Id
    @Column(name = "sDlId")
    private int sDlId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSnId() {
        return snId;
    }

    public void setSnId(int snId) {
        this.snId = snId;
    }

    public int getsDlId() {
        return sDlId;
    }

    public void setsDlId(int sDlId) {
        this.sDlId = sDlId;
    }
}

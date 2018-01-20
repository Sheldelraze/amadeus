package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mr.Minh
 * @since 20/01/2018
 * Purpose:
 */
@Table(name="pm_it")
public class PmItEntity extends BaseEntity {
    static final long serialVersionUID = 64211943L;
    @Id
    @Column(name = "pmId")
    private int pmId;
    @Id
    @Column(name = "itId")
    private int itId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPmId() {
        return pmId;
    }

    public void setPmId(int pmId) {
        this.pmId = pmId;
    }

    public int getItId() {
        return itId;
    }

    public void setItId(int itId) {
        this.itId = itId;
    }
}

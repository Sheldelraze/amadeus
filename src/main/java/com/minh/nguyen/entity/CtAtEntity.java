package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 19/02/2018
 * Purpose:
 */
@Table(name = "ct_at")
public class CtAtEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 4439153252634L;

    @Id
    @Column(name = "ctId")
    private Integer ctId;

    @Id
    @Column(name = "atId")
    private Integer atId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public Integer getAtId() {
        return atId;
    }

    public void setAtId(Integer atId) {
        this.atId = atId;
    }
}

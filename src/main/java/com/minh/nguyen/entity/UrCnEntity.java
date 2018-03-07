package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 07/03/2018
 * Purpose:
 */
@Table(name = "ur_cn")
public class UrCnEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 50042L;

    @Id
    @Column(name = "urId")
    private Integer urId;

    @Id
    @Column(name = "cnId")
    private Integer cnId;

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public Integer getCnId() {
        return cnId;
    }

    public void setCnId(Integer cnId) {
        this.cnId = cnId;
    }
}

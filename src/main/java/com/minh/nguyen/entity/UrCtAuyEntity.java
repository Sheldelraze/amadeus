package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 07/02/2018
 * Purpose:
 */
@Table(name = "ur_ct_auy")
public class UrCtAuyEntity  extends BaseEntity implements Serializable {
    static final long serialVersionUID = 9343L;

    @Id
    @Column(name = "ctId")
    private Integer ctId;

    @Id
    @Column(name = "urId")
    private Integer urId;

    @Id
    @Column(name = "auyId")
    private Integer auyId;

    @Column(name = "rank")
    private Integer rank;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public Integer getAuyId() {
        return auyId;
    }

    public void setAuyId(Integer auyId) {
        this.auyId = auyId;
    }
}

package com.minh.nguyen.entity;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 12/03/2018
 * Purpose:
 */
@Table(name = "ur_me")
public class UrMeEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3330039343L;

    private Integer urId;

    private Integer meId;

    private Integer isRead;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUrId() {
        return urId;
    }

    public void setUrId(Integer urId) {
        this.urId = urId;
    }

    public Integer getMeId() {
        return meId;
    }

    public void setMeId(Integer meId) {
        this.meId = meId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}

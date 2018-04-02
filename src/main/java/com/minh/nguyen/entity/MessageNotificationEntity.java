package com.minh.nguyen.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 12/03/2018
 * Purpose:
 */
@Table(name = "messagenotification")
public class MessageNotificationEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3330039343L;

    @Id
    @Column(name = "urId")
    private Integer urId;

    @Id
    @Column(name = "meId")
    private Integer meId;

    @Column(name = "isRead")
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

package com.minh.nguyen.Entity;

import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose:
 */
@Table(name = "apply")
public class ApplyEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 464924412L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "stId")
    private Integer stId;

    @Id
    @Column(name = "ceId")
    private Integer ceId;

    @Column(name = "sentDate")
    private LocalDateTime sentDate;

    @Column(name = "isResolved")
    private Integer isResolved;

    @Column(name = "description")
    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStId() {
        return stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public Integer getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(Integer isResolved) {
        this.isResolved = isResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

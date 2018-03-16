package com.minh.nguyen.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
@Table(name = "material")
public class MaterialEntity extends BaseEntity implements Serializable {
    static final long serialVersionUID = 643412904214309L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sutId")
    private Integer sutId;

    @Column(name = "name")
    private String name;

    @Column(name = "storedLocation")
    private String storedLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "downloadCnt")
    private Integer downloadCnt;

    @Column(name = "isPublic")
    private Integer isPublic;

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSutId() {
        return sutId;
    }

    public void setSutId(Integer sutId) {
        this.sutId = sutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoredLocation() {
        return storedLocation;
    }

    public void setStoredLocation(String storedLocation) {
        this.storedLocation = storedLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDownloadCnt() {
        return downloadCnt;
    }

    public void setDownloadCnt(Integer downloadCnt) {
        this.downloadCnt = downloadCnt;
    }
}

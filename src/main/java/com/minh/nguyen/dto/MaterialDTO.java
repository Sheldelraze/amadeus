package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 16/03/2018
 * Purpose:
 */
public class MaterialDTO extends BaseDTO {
    private Integer id;

    private Integer sutId;

    private String name;

    private String description;

    private Integer status;

    private Integer isPublic;

    private String storedLocation;

    private Integer downloadCnt;

    public Integer getSutId() {
        return sutId;
    }

    public void setSutId(Integer sutId) {
        this.sutId = sutId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public String getStoredLocation() {
        return storedLocation;
    }

    public void setStoredLocation(String storedLocation) {
        this.storedLocation = storedLocation;
    }

    public Integer getDownloadCnt() {
        return downloadCnt;
    }

    public void setDownloadCnt(Integer downloadCnt) {
        this.downloadCnt = downloadCnt;
    }
}

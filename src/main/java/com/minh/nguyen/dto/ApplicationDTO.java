package com.minh.nguyen.dto;

/**
 * @author Mr.Minh
 * @since 21/03/2018
 * Purpose:
 */
public class ApplicationDTO extends BaseDTO {
    private Integer id;
    private UserDTO sender;
    private Integer ceId;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public Integer getCeId() {
        return ceId;
    }

    public void setCeId(Integer ceId) {
        this.ceId = ceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

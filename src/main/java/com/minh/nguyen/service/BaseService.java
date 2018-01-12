package com.minh.nguyen.service;

import com.minh.nguyen.entity.BaseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.RollbackException;
import java.util.Calendar;
import java.util.Date;

@Service
public class BaseService<T> {
    void setCreateInfo(BaseEntity entity){
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setCreateClass(BaseService.class.toString());
        entity.setCreateTime(time);
        entity.setCreateUser("minh.nt");
    }
    void setUpdateInfo(BaseEntity entity){
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setUpdateClass(BaseService.class.toString());
        entity.setUpdateTime(time);
        entity.setUpdateUser("minh.nt");
    }
    public void rollBack(String errMessage) {
        RollbackException ex = new RollbackException(errMessage);
        throw ex;
    }
}

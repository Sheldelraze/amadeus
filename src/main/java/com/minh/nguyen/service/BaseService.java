package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.entity.BaseEntity;
import com.minh.nguyen.util.CheckUtil;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BaseService<T> {
    protected ModelMapper modelMapper;

    protected List<String> exclusiveUpdateField;

    @Autowired
    private CheckUtil checkUtil;
    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
        exclusiveUpdateField = new ArrayList<>();
        exclusiveUpdateField.add("createClass");
        exclusiveUpdateField.add("createUser");
        exclusiveUpdateField.add("createTime");
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> pContext) {
                if (null == pContext.getSource() || null == pContext.getDestinationType()) {
                    return false;
                }
                if (pContext.getSourceType().equals(String.class)) {
                    if (Constants.BLANK.equals(pContext.getSource().toString())) {
                        return false;
                    }
                }
                if (pContext.getSourceType().equals(String.class)
                        && (Integer.class.equals(pContext.getDestinationType())
                        || int.class.equals(pContext.getDestinationType()))) {
                    if (checkUtil.isInteger(pContext.getSource().toString())) {
                        return true;
                    }
                    return false;
                }
                return true;
            }

        });

    }
    void setCreateInfo(BaseEntity entity){
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setCreateClass(BaseService.class.toString());
        entity.setCreateTime(time);
        entity.setCreateUser("minh.nt");
        entity.setDeleteFlg("0");
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

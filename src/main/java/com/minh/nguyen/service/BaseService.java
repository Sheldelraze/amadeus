package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.entity.BaseEntity;
import com.minh.nguyen.util.CheckUtil;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.RollbackException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mr.Minh
 * @since 06/02/2018
 * Purpose: base class for all service class
 */
@Service
@Primary
public class BaseService {
    public ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                Date Date = null;
                try {
                    Date = formatter.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return Date;
            }
        };
        modelMapper.createTypeMap(String.class, Date.class);
        modelMapper.addConverter(toStringDate);
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> pContext) {
                if (null == pContext.getSource() || null == pContext.getDestinationType()) {
                    return false;
                }
                if (pContext.getSourceType().equals(String.class)) {
                    if (Constants.BLANK.equals(pContext.getSource().toString())) {
                        return false;
                    }
                    if(pContext.getDestinationType().equals(Date.class)){
                        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        try {
                            formatter.parse(pContext.getSource().toString());
                            return true;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }
                if (pContext.getSourceType().equals(String.class)
                        && (pContext.getDestinationType().equals(Integer.class))
                        || int.class.equals(pContext.getDestinationType())) {
                    if (CheckUtil.isInteger(pContext.getSource().toString())) {
                        return true;
                    }
                    return false;
                }
                return true;
            }

        });

    }
    void setCreateInfo(BaseEntity entity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setCreateClass(this.getClass().getName());
        entity.setCreateTime(time);
        entity.setCreateUser(currentUser);
        entity.setDeleteFlg("0");
    }
    void setUpdateInfo(BaseEntity entity){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setUpdateClass(this.getClass().getName());
        entity.setUpdateTime(time);
        entity.setUpdateUser(currentUser);
    }

    public void rollBack(String errMessage) {
        RollbackException ex = new RollbackException(errMessage);
        throw ex;
    }
}

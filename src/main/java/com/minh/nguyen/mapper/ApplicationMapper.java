package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.ApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("ApplicationMapper")
public interface ApplicationMapper extends BaseMapper<ApplicationEntity> {
}

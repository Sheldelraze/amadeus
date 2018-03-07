package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.UrCnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("UrCnMapper")
@Mapper
public interface UrCnMapper extends BaseMapper<UrCnEntity> {
}

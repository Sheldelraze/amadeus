package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.UrMeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("UrMeMapper")
public interface UrMeMapper extends BaseMapper<UrMeEntity> {
}

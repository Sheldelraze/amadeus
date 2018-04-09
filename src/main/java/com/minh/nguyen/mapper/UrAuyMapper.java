package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.UrAuyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("UrAuyMapper")
public interface UrAuyMapper extends BaseMapper<UrAuyEntity> {
}

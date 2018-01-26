package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.SnSDlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("SnSDlMapper")
@Mapper
public interface SnSDlMapper extends BaseMapper<SnSDlEntity> {
}

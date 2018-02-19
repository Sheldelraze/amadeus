package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.CtAtEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("CtAtMapper")
@Mapper
public interface CtAtMapper extends BaseMapper<CtAtEntity> {
}

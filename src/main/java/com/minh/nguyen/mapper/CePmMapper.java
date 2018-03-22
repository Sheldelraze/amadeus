package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.CePmEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("CePmMapper")
public interface CePmMapper extends BaseMapper<CePmEntity> {
}

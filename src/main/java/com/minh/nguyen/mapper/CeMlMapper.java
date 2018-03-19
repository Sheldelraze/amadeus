package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.CeMlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("CeMlMapper")
public interface CeMlMapper extends BaseMapper<CeMlEntity> {
}

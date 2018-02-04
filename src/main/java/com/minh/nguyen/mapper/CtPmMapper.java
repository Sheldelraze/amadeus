package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.CtPmEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("CtPmMapper")
@Mapper
public interface CtPmMapper extends BaseMapper<CtPmEntity>{
}

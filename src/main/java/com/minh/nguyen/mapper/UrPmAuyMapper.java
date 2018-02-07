package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.UrPmAuyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("UrPmAuyMapper")
@Mapper
public interface UrPmAuyMapper extends BaseMapper<UrPmAuyEntity>{
}

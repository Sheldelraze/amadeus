package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.CeSnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("CeSnMapper")
public interface CeSnMapper extends BaseMapper<CeSnEntity> {
}

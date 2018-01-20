package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.PmItEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("PmItMapper")
@Mapper
public interface PmItMapper extends BaseMapper<PmItEntity> {
}

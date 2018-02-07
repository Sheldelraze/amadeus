package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.UrCtAuyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("UrCtAuyMapper")
@Mapper
public interface UrCtAuyMapper extends BaseMapper<UrCtAuyEntity> {
}

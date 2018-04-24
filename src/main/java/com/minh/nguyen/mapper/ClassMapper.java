package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.ClassEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("ClassMapper")
public interface ClassMapper extends BaseMapper<ClassEntity> {
}

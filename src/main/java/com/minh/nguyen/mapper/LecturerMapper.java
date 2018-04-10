package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.LecturerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("LecturerMapper")
public interface LecturerMapper extends BaseMapper<LecturerEntity> {
}

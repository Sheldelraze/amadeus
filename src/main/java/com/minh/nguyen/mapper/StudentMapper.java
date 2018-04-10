package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("StudentMapper")
public interface StudentMapper extends BaseMapper<StudentEntity> {
}

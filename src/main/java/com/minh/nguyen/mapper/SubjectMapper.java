package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.SubjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("SubjectMapper")
public interface SubjectMapper extends BaseMapper<SubjectEntity> {
}

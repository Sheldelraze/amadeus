package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.SubmitDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("SubmitDetailMapper")
@Mapper
public interface SubmitDetailMapper extends BaseMapper<SubmitDetailEntity>{
}

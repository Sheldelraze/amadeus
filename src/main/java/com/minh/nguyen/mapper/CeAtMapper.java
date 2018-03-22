package com.minh.nguyen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("CeAtMapper")
public interface CeAtMapper extends BaseMapper<CeAtMapper> {
}

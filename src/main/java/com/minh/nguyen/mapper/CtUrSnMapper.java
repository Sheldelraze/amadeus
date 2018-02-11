package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.CtUrSnEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Minh
 * @since 11/02/2018
 * Purpose:
 */
@Component("CtUrSnMapper")
@Mapper
public interface CtUrSnMapper extends BaseMapper<CtUrSnEntity> {
}

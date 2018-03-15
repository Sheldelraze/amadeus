package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.UrCeAuyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Minh
 * @since 15/03/2018
 * Purpose:
 */
@Component("UrCeAuyMapper")
@Mapper
public interface UrCeAuyMapper extends BaseMapper<UrCeAuyEntity> {
}

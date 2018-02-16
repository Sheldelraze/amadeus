package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Minh
 * @since 10/01/2018
 * Purpose:
 */
@Component("RoleMapper")
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
}

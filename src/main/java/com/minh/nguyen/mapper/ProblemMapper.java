package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.ProblemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author Mr.Minh
 * @since 10/01/2018
 * Purpose:
 */
@Component("ProblemMapper")
@Mapper
public interface ProblemMapper extends BaseMapper<ProblemEntity> {

}

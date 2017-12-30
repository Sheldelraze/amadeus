package com.minh.nguyen.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMapper<T> {
    T findByPK(T entity);
    int insert(T entity);
    int updateByPK(T entity);
}

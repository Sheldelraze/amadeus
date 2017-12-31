package com.minh.nguyen.Mapper;

import com.minh.nguyen.Provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface BaseMapper<T> {
    @SelectProvider(type = BaseProvider.class, method = "findByPK")
    T findByPK(T entity);
    @InsertProvider(type = BaseProvider.class, method = "insert")
    int insert(T entity);
    @UpdateProvider(type = BaseProvider.class, method = "updateByPK")
    int updateByPK(T entity);
}

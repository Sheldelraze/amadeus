package com.minh.nguyen.mapper;

import com.minh.nguyen.provider.BaseProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose: provide base prepared SQL statement
 */
@Mapper
public interface BaseMapper<T> {
    /**
     * @since 01-01-2018
     * @param entity
     * @return
     */
    @SelectProvider(type = BaseProvider.class, method = "selectByPK")
    List<T> selectByPK(@Param("entity") T entity);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    int insert(@Param("entity") T entity);

    @UpdateProvider(type = BaseProvider.class, method = "updateByPK")
    int updateByPK(@Param("entity") T entity);

}

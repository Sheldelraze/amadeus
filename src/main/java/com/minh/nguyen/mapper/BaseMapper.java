package com.minh.nguyen.mapper;

import com.minh.nguyen.provider.BaseProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose: provide base prepared SQL statement
 */
public interface BaseMapper<T> {

    @SelectProvider(type = BaseProvider.class, method = "selectByPrimaryKey")
    T selectByPK(T record);

    @SelectProvider(type = BaseProvider.class, method = "selectAll")
    List<T> selectAll(Class<?> entityClass);

    @SelectProvider(type = BaseProvider.class, method = "selectWithExample")
    List<T> selectWithExample(T record);

    @SelectProvider(type = BaseProvider.class, method = "existPrimaryKey")
    Boolean existWithPK(T record);

    @SelectProvider(type = BaseProvider.class, method = "existWithExample")
    Integer countWithExample(T record);

    @DeleteProvider(type = BaseProvider.class, method = "deleteByPrimaryKey")
    Integer deleteByPK(T record);

    @DeleteProvider(type = BaseProvider.class, method = "deleteWithExample")
    Integer deleteWithExample(T record);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    Integer insert(T record);

    @UpdateProvider(type = BaseProvider.class, method = "updateByPrimaryKey")
    Integer updateByPK(T record);

    @UpdateProvider(type = BaseProvider.class, method = "updateByPKExceptFields")
    Integer updateByPKExceptNullFields(T entity);

    @SelectProvider(type = BaseProvider.class, method = "checkExclusive")
    Boolean checkExclusive(T record);

}

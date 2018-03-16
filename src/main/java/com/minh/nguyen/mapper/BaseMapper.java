package com.minh.nguyen.mapper;

import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 31/12/2017
 * Purpose: provide base SQL function for every table
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
    Integer deleteLogicWithExample(T record);

    @DeleteProvider(type = BaseProvider.class, method = "deleteForRealByExample")
    Integer deleteForRealWithExample(T record);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    Integer insert(T record);

    @UpdateProvider(type = BaseProvider.class, method = "updateByPrimaryKey")
    Integer updateByPK(T record);

    @UpdateProvider(type = BaseProvider.class, method = "updateByPKExceptFields")
    Integer updateNotNullByPK(T entity);

    @SelectProvider(type = BaseProvider.class, method = "checkExclusive")
    Boolean checkExclusive(T record);

}

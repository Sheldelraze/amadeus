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
    /**
     * get one record by primary key
     *
     * @param record T
     * @return T
     */
    @SelectProvider(type = BaseProvider.class, method = "selectByPrimaryKey")
    T selectByPK(T record);

    /**
     * get all record from table
     *
     * @param entityClass Class<?>
     * @return List<T>
     */
    @SelectProvider(type = BaseProvider.class, method = "selectAll")
    List<T> selectAll(Class<?> entityClass);

    /**
     * select records with example info
     *
     * @param record T
     * @return List<T>
     */
    @SelectProvider(type = BaseProvider.class, method = "selectWithExample")
    List<T> selectWithExample(T record);

    /**
     * check exist record with primary key info
     *
     * @param record T
     * @return Boolean
     */
    @SelectProvider(type = BaseProvider.class, method = "existPrimaryKey")
    Boolean existWithPK(T record);

    /**
     * count record with primary key info
     *
     * @param record T
     * @return Integer
     */
    @SelectProvider(type = BaseProvider.class, method = "existWithExample")
    Integer countWithExample(T record);

    /**
     * delete record with primary key info
     *
     * @param record T
     * @return Integer
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteByPrimaryKey")
    Integer deleteByPK(T record);

    /**
     * delete records with example info
     *
     * @param record T
     * @return Integer
     */
    @DeleteProvider(type = BaseProvider.class, method = "deleteWithExample")
    Integer deleteWithExample(T record);

    /**
     * insert record into
     *
     * @param record T
     * @return Integer
     */
    @InsertProvider(type = BaseProvider.class, method = "insert")
    Integer insert(T record);

    /**
     * update record with primary key
     *
     * @param record T
     * @return Integer
     */
    @UpdateProvider(type = BaseProvider.class, method = "updateByPrimaryKey")
    Integer updateByPK(T record);

    /**
     * check exclusive
     *
     * @param record T
     * @return Boolean
     */
    @SelectProvider(type = BaseProvider.class, method = "checkExclusive")
    Boolean checkExclusive(T record);

}

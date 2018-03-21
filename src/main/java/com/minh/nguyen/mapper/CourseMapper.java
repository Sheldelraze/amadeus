package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.CourseEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component("CourseMapper")
@Mapper
public interface CourseMapper extends BaseMapper<CourseEntity> {

    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertCourse(CourseEntity record);

    Integer countApplication(@Param("ceId") Integer ceId);

}

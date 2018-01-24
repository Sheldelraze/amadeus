package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Component("ContestMapper")
@Mapper
public interface ContestMapper extends BaseMapper<ContestEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertContest(ContestEntity record);
}
package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.MaterialEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Mapper
@Component("MaterialMapper")
public interface MaterialMapper extends BaseMapper<MaterialEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertContest(MaterialEntity record);
}

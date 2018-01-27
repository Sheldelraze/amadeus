package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.SubmitDetailEntity;
import com.minh.nguyen.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Component("SubmitDetailMapper")
@Mapper
public interface SubmitDetailMapper extends BaseMapper<SubmitDetailEntity>{
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertSubmitDetail(SubmitDetailEntity record);
}

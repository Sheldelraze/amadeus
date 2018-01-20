package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.InputDTO;
import com.minh.nguyen.entity.InputEntity;
import com.minh.nguyen.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("InputMapper")
@Mapper
public interface InputMapper extends BaseMapper<InputEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertInput(InputEntity record);

    List<InputDTO> getAllTest(@Param("pmId")Integer pmId);
}

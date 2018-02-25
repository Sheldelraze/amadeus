package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.ContestDTO;
import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ContestMapper")
@Mapper
public interface ContestMapper extends BaseMapper<ContestEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertContest(ContestEntity record);

    List<ContestDTO> getAllContest(@Param("auth_participate_contest_id") Integer participateContestAuthId);
}

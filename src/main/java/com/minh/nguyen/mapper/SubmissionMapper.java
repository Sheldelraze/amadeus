package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("SubmissionMapper")
@Mapper
public interface SubmissionMapper extends BaseMapper<SubmissionEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertSubmission(SubmissionEntity record);

    List<SubmissionDTO> getSubmission();

    List<SubmissionDTO> getSubmitDetail(@Param("snId")Integer snId);

    List<SubmissionDTO> getSubmissionInContest(@Param("ctId")Integer ctId,@Param("handle")String handle);

    List<SubmissionDTO> getSubmissionInCourse(@Param("ceId") Integer ceId, @Param("handle") String handle);

    List<SubmissionDTO> getAllSolvedProblem(@Param("urId")Integer urId);

    Integer checkSolvedStatusInContest(@Param("ctId")Integer ctId, @Param("pmId")Integer pmId, @Param("urId")Integer urId);

    Integer checkSolvedStatusInCourse(@Param("ceId")Integer ceId, @Param("pmId")Integer pmId, @Param("urId")Integer urId);
}

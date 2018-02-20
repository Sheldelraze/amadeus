package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.entity.ProblemEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author Mr.Minh
 * @since 10/01/2018
 * Purpose:
 */
@Component("ProblemMapper")
@Mapper
public interface ProblemMapper extends BaseMapper<ProblemEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertEntity(ProblemEntity record);

    List<ProblemDTO> getAllOfMyProblem(@Param("urId") Integer urId, @Param("read_problem_auyId") Integer readProblemAuyID);

    List<ProblemDTO> getAllPublicProblem();

    List<ProblemDTO> getProblemForContest(@Param("can_view_problem_auth")Integer canVieProblemAuth,@Param("ctId") Integer ctId);

    List<ProblemDTO> getProblemToDisplay(@Param("ctId") Integer ctId, @Param("getAllProblem") Boolean getAllProblem);

    List<ProblemDTO> getProblemToSubmit(@Param("ctId")Integer ctId);

    List<ProblemDTO> getProblemForLeaderboard(@Param("ctId")Integer ctId);

    Integer checkIfSolvedBefore(@Param("pmId") Integer pmId,@Param("urId")Integer urId);

    Integer resetFirstSolveTime(@Param("pmId")Integer pmId);
}

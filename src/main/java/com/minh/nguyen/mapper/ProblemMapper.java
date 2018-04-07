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

    List<ProblemDTO> getProblemForContest(@Param("urId") Integer urId, @Param("can_view_problem_auth") Integer canVieProblemAuth, @Param("ctId") Integer ctId);

    List<ProblemDTO> getProblemToDisplayInContest(@Param("ctId") Integer ctId, @Param("getAllProblem") Boolean getAllProblem);

    List<ProblemDTO> getProblemToSubmitInContest(@Param("ctId") Integer ctId);

    List<ProblemDTO> getProblemForCourse(@Param("urId") Integer urId, @Param("can_view_problem_auth") Integer canVieProblemAuth, @Param("ceId") Integer ceId);

    List<ProblemDTO> getProblemToDisplayInCourse(@Param("ceId") Integer ceId, @Param("getAllProblem") Boolean getAllProblem);

    List<ProblemDTO> getProblemToSubmitInCourse(@Param("ceId") Integer ceId);

    List<ProblemDTO> getProblemForLeaderboardInContest(@Param("ctId")Integer ctId);

    List<ProblemDTO> getProblemForLeaderboardInCourse(@Param("ceId")Integer ceId);

    Integer checkIfSolvedBefore(@Param("pmId") Integer pmId,@Param("urId")Integer urId);

    Integer checkIfSolvedBeforeInContest(@Param("pmId") Integer pmId,@Param("ctId")Integer ctId,@Param("urId")Integer urId);

    Integer checkIfSolvedBeforeInCourse(@Param("pmId") Integer pmId,@Param("ceId")Integer ceId,@Param("urId")Integer urId);

}

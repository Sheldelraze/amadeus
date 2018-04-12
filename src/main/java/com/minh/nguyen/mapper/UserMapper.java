package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.StudentDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UserMapper")
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    UserDTO findUserByHandle(@Param("handle") String handle);

    UserDTO findUserById(@Param("urId") Integer urId);

    List<UserDTO> getUserInformationWhenLogin(@Param("handle") String handle);

    List<UserDTO> getLeaderboardIOIForContest(@Param("ctId") Integer ctId, @Param("auth_participate_id") Integer authParticipateId);

    List<UserDTO> getLeaderboardACMForContest(@Param("ctId") Integer ctId, @Param("auth_participate_id") Integer authParticipateId,@Param("acceptedStatus")Integer acceptedStatus);

    List<UserDTO> getLeaderboardInformationForCourse(@Param("ceId") Integer ceId, @Param("auth_participate_id") Integer authParticipateId,@Param("acceptedStatus")Integer acceptedStatus);

    List<UserDTO> findUserForProblemRole(@Param("fullname") String fullname, @Param("reId") Integer reId, @Param("pmId") Integer pmId);

    List<UserDTO> findUserForContestRole(@Param("fullname") String fullname, @Param("reId") Integer reId, @Param("ctId") Integer ctId);

    List<UserDTO> findUserForCourseRole(@Param("fullname") String fullname, @Param("reId") Integer reId, @Param("ceId") Integer ceId);

    List<UserDTO> getListProblemRole(@Param("urId") Integer urId, @Param("pmId") Integer pmId);

    List<UserDTO> getListContestRole(@Param("urId") Integer urId, @Param("ctId") Integer ctId);

    List<UserDTO> getListCourseRole(@Param("urId") Integer urId, @Param("ceId") Integer ceId);

    List<UserDTO> findListUserByFullnameOrHandle(@Param("text") String textm, @Param("urId") Integer currentUserID, @Param("from") Integer limitFrom, @Param("size") Integer maxUserPerFetch);

    List<UserDTO> findUserInConversation(@Param("topic") String topic);

    List<StudentDTO> getTopUser(@Param("role_student_id")Integer roleStudentID);

    Integer getRoleForUser(@Param("urId")Integer urId);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertUser(UserEntity record);
}

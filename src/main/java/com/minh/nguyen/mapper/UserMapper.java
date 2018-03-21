package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UserMapper")
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    UserDTO findUserByHandle(@Param("handle") String handle);

    UserDTO findUserById(@Param("urId") Integer urId);

    List<UserDTO> getUserAuthority(@Param("handle") String handle);

    List<UserDTO> getLeaderboardInfor(@Param("ctId") Integer ctId, @Param("auth_participate_id") Integer authParticipateId);

    List<UserDTO> findUserForProblemRole(@Param("fullname") String fullname, @Param("reId") Integer reId, @Param("pmId") Integer pmId);

    List<UserDTO> findUserForContestRole(@Param("fullname") String fullname, @Param("reId") Integer reId, @Param("ctId") Integer ctId);

    List<UserDTO> findUserForCourseRole(@Param("fullname") String fullname, @Param("reId") Integer reId, @Param("ceId") Integer ceId);

    List<UserDTO> getListProblemRole(@Param("urId") Integer urId, @Param("pmId") Integer pmId);

    List<UserDTO> getListContestRole(@Param("urId") Integer urId, @Param("ctId") Integer ctId);

    List<UserDTO> getListCourseRole(@Param("urId") Integer urId, @Param("ceId") Integer ceId);

    List<UserDTO> findListUserByFullnameOrHandle(@Param("text") String textm, @Param("urId") Integer currentUserID, @Param("from") Integer limitFrom, @Param("size") Integer maxUserPerFetch);

    List<UserDTO> findUserInConversation(@Param("topic") String topic);
}

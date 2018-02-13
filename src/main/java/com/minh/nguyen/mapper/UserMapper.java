package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("UserMapper")
@Mapper
public interface UserMapper extends BaseMapper<UserEntity>{
    List<UserDTO> getUserAuthority(@Param("handle") String handle);

    List<UserDTO> getLeaderboardInfor(@Param("ctId")Integer ctId,@Param("auth_participate_id")Integer authParticipateId);
}

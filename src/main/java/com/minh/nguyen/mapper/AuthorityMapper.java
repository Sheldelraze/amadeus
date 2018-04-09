package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.entity.AuthorityEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("AuthorityMapper")
@Mapper
public interface AuthorityMapper extends BaseMapper<AuthorityEntity> {
    List<AuthorityDTO> getProblemAuthority(@Param("pmId") Integer pmId, @Param("handle") String handle);

    List<AuthorityDTO> getContestAuthority(@Param("ctId") Integer ctId, @Param("handle") String handle);

    List<AuthorityDTO> getCourseAuthority(@Param("ceId") Integer ceId, @Param("handle") String handle);

    List<AuthorityDTO> getDefaultAuthorityForRole(@Param("reId") Integer reId);

}

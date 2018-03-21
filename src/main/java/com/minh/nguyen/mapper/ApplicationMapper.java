package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.ApplicationDTO;
import com.minh.nguyen.entity.ApplicationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("ApplicationMapper")
public interface ApplicationMapper extends BaseMapper<ApplicationEntity> {
    List<ApplicationDTO> getAllApplicationInCourse(@Param("ceId") Integer ceId);
}

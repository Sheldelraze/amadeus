package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.LanguageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LanguageMapper")
@Mapper
public interface LanguageMapper extends BaseMapper<LanguageEntity>{
    List<String> getLanguageName(@Param("leId") Integer leId);
}

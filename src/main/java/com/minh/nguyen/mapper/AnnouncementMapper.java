package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.AnnouncementDTO;
import com.minh.nguyen.entity.AnnouncementEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("AnnouncementMapper")
@Mapper
public interface AnnouncementMapper extends BaseMapper<AnnouncementEntity> {
    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertAnnouncement(AnnouncementEntity record);

    Integer countAnnouncementList(@Param("ctId") Integer ctId, @Param("getAllAnnounce") Boolean getAllAnnounce);

    List<AnnouncementDTO> getAnnouncementList(@Param("ctId") Integer ctId, @Param("getAllAnnounce") Boolean getAllAnnounce);
}

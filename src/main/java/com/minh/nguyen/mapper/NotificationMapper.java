package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.NotificationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("NotificationMapper")
public interface NotificationMapper extends BaseMapper<NotificationEntity> {

}

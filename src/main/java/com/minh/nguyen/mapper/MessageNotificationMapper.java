package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.MessageNotificationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("UrMeMapper")
public interface MessageNotificationMapper extends BaseMapper<MessageNotificationEntity> {
}

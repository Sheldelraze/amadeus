package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.ConversationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component("ConversationMapper")
@Mapper
public interface ConversationMapper extends BaseMapper<ConversationMapper> {
    ConversationEntity selectByTopic(@Param("topic") String topic);
}

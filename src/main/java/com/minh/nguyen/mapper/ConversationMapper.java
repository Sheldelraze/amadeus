package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.ConversationEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component("ConversationMapper")
@Mapper
public interface ConversationMapper extends BaseMapper<ConversationMapper> {
    ConversationEntity selectByTopic(@Param("topic") String topic);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertConversation(ConversationEntity record);
}

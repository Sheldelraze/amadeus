package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.entity.MessageEntity;
import com.minh.nguyen.mapper.provider.BaseProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MessageMapper")
@Mapper
public interface MessageMapper extends BaseMapper<MessageEntity> {

    //get recently Constants.MAX_MESSAGE_PER_FETCH messages
    List<MessageDTO> getRecentMessage(@Param("topic") String topic
            , @Param("from") Integer limitFrom, @Param("size") Integer messagePerFetch, @Param("urId") Integer currentUserId);

    List<MessageDTO> getPublicMessage(@Param("topic") String topic
            , @Param("from") Integer limitFrom, @Param("size") Integer messagePerFetch);

    Integer updateMessageStatus(@Param("topic") String topic, @Param("urId") Integer urId);

    List<MessageDTO> getMessageNotify(@Param("urId") Integer urId);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insertMessage(MessageEntity record);
}

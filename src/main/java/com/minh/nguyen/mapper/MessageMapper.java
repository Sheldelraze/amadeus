package com.minh.nguyen.mapper;

import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MessageMapper")
@Mapper
public interface MessageMapper extends BaseMapper<MessageEntity> {

    //get recently Constants.MAX_MESSAGE_PER_FETCH messages
    List<MessageDTO> getRecentMessage(@Param("topic") String topic
            , @Param("from") Integer limitFrom, @Param("size") Integer messagePerFetch);
}

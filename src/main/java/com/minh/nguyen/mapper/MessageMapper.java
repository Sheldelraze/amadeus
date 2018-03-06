package com.minh.nguyen.mapper;

import com.minh.nguyen.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("MessageMapper")
@Mapper
public interface MessageMapper extends BaseMapper<MessageEntity> {
}

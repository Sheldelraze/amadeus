package com.minh.nguyen.service;

import com.google.common.collect.Lists;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.entity.ConversationEntity;
import com.minh.nguyen.entity.MessageEntity;
import com.minh.nguyen.mapper.ConversationMapper;
import com.minh.nguyen.mapper.MessageMapper;
import com.minh.nguyen.mapper.UrCtAuyMapper;
import com.minh.nguyen.util.CheckUtil;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 06/03/2018
 * Purpose:
 */
@Service("MessageService")
public class MessageService extends BaseService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private UrCtAuyMapper urCtAuyMapper;

    @Async
    public void insertMessage(MessageDTO message, String topic) {
        ConversationEntity conversationEntity = conversationMapper.selectByTopic(topic);
        if (conversationEntity == null || StringUtil.isNull(message.getUrId()) || !CheckUtil.isInteger(message.getUrId())) {
            return;
        }
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setCreateTime(message.getCreateTime());
        messageEntity.setDeleteFlg("0");
        messageEntity.setContent(message.getContent());
        messageEntity.setCnId(conversationEntity.getId());
        messageEntity.setUrId(Integer.parseInt(message.getUrId()));
        messageMapper.insert(messageEntity);
    }

    public List<MessageDTO> getRecentMessage(String topic) {
        List<MessageDTO> lstMessage = messageMapper.getRecentMessage(topic, Constants.MAX_RECENT_MESSAGE);
        for (MessageDTO message : lstMessage) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            message.setSendTime(dateFormat.format(message.getCreateTime()));
        }
        lstMessage = Lists.reverse(lstMessage);
        return lstMessage;
    }
}

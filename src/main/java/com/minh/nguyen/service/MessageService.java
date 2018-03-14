package com.minh.nguyen.service;

import com.google.common.collect.Lists;
import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.ConversationDTO;
import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.CheckUtil;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 06/03/2018
 * Purpose:
 */
@Service("MessageService")
public class MessageService extends BaseService {

    @Autowired
    private SimpMessageSendingOperations simpMessagingTemplate;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ConversationMapper conversationMapper;

    @Autowired
    private UrCtAuyMapper urCtAuyMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UrCnMapper urCnMapper;

    @Autowired
    private UrMeMapper urMeMapper;

    @Async
    public void insertMessage(MessageDTO message, String topic) {
        if (StringUtil.isNull(message.getUrId()) || !CheckUtil.isInteger(message.getUrId())) {
            return;
        }
        ConversationEntity conversationEntity = conversationMapper.selectByTopic(topic);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setCreateTime(message.getCreateTime());
        messageEntity.setDeleteFlg("0");
        messageEntity.setContent(message.getContent());
        messageEntity.setCnId(conversationEntity.getId());
        messageEntity.setUrId(Integer.parseInt(message.getUrId()));
        messageMapper.insertMessage(messageEntity);

        //insert notification
        if (!topic.equals(Constants.PUBLIC_TOPIC) && !topic.equals(Constants.DEFAULT_TOPIC)) {
            String urId = message.getUrId();
            String[] lstUrId = topic.split("_");
            UrMeEntity urMeEntity = new UrMeEntity();
            urMeEntity.setDeleteFlg("0");
            urMeEntity.setMeId(messageEntity.getId());
            urMeEntity.setCreateTime(message.getCreateTime());
            for (String id : lstUrId) {
                if (!id.equals(urId)) {
                    urMeEntity.setIsRead(Constants.MESSAGE_NOT_READ_FLAG);
                    sendMessageNotify(message, id);
                } else {
                    urMeEntity.setIsRead(Constants.MESSAGE_READ_FLAG);
                }
                urMeEntity.setUrId(Integer.parseInt(id));
                urMeMapper.insert(urMeEntity);
            }
        }

    }

    private void sendMessageNotify(MessageDTO message, String receiverId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Integer.parseInt(message.getUrId()));
        userEntity = userMapper.selectByPK(userEntity);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setFullname(userEntity.getFullname());
        message.setSender(userDTO);
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.MESSAGE_NOTIFICATION_TOPIC + receiverId, message);
    }

    public List<MessageDTO> getMessageNotify(Integer urId) {
        List<MessageDTO> lstMessage = messageMapper.getMessageNotify(urId);
        for (MessageDTO message : lstMessage) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            message.setSendTime(dateFormat.format(message.getCreateTime()));
        }
        return lstMessage;
    }
    public String getUserInConversation(String topic) {
        //get conversation ID
        ConversationEntity conversationEntity = conversationMapper.selectByTopic(topic);
        if (conversationEntity == null) {
            conversationEntity = new ConversationEntity();
            conversationEntity.setTopic(topic);
            conversationEntity.setCreateTime(new Date());
            conversationEntity.setDeleteFlg("0");
            conversationMapper.insertConversation(conversationEntity);

            //if topic != null then insert all user in conversation
            if (!topic.equals(Constants.PUBLIC_TOPIC) && !topic.equals(Constants.DEFAULT_TOPIC)) {
                String[] lstUrId = topic.split("_");
                UrCnEntity urCnEntity = new UrCnEntity();
                urCnEntity.setCnId(conversationEntity.getId());
                urCnEntity.setCreateTime(new Date());
                urCnEntity.setDeleteFlg("0");
                for (String urId : lstUrId) {
                    Integer id = Integer.parseInt(urId);
                    urCnEntity.setUrId(id);
                    urCnMapper.insert(urCnEntity);
                }
            }
        }

        List<UserDTO> lstUser = userMapper.findUserInConversation(topic);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lstUser.size(); i++) {
            UserDTO user = lstUser.get(i);
            stringBuilder.append(user.getFullname());
            if (i != lstUser.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    public List<MessageDTO> getRecentMessage(String topic, Integer limitFrom, Integer currentUserId) {
        List<MessageDTO> lstMessage = null;
        if (currentUserId != null) {
            lstMessage = messageMapper.getRecentMessage(topic, limitFrom, Constants.MAX_MESSAGE_PER_FETCH, currentUserId);
        } else {
            lstMessage = messageMapper.getPublicMessage(topic, limitFrom, Constants.MAX_MESSAGE_PER_FETCH);
        }
        for (MessageDTO message : lstMessage) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            message.setSendTime(dateFormat.format(message.getCreateTime()));
        }
        lstMessage = Lists.reverse(lstMessage);
        return lstMessage;
    }

    public List<UserDTO> getLstUser(String inputText, Integer currentUserId, Integer limitFrom) {
        List<UserDTO> lstUser = userMapper.findListUserByFullnameOrHandle(inputText, currentUserId, limitFrom, Constants.MAX_USER_PER_SEARCH);
        for (UserDTO user : lstUser) {
            Integer urId = user.getId();
            int userLogginId = currentUserId;
            if (urId > userLogginId) {
                int tg = urId;
                urId = userLogginId;
                userLogginId = tg;
            }
            if (user.getConversation() == null) {
                user.setConversation(new ConversationDTO());
            }
            user.getConversation().setTopic(urId + "_" + userLogginId);
        }
        return lstUser;
    }

    public void updateMessageStatus(String topic, Integer urId) {
        messageMapper.updateMessageStatus(topic, urId);
    }
}

package com.minh.nguyen.controller;

import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 03/03/2018
 * Purpose:
 */
@Controller
public class MessageController extends BaseController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping({"/message", "/message/"})
    public ModelAndView getMessageView() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("other/message");
        return modelAndView;
    }

    @MessageMapping("/message/send")
    @SendTo("/message/topic/public")
    public MessageDTO sendMessage(@Payload MessageDTO chatMessage) {
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
        return chatMessage;
    }

    @MessageMapping("/message/send.{channelId}")
    @SendTo("/message/topic.{channelId}")
    public MessageDTO chatMessage(@DestinationVariable String channelId, MessageDTO message) {

        return message;
    }
}

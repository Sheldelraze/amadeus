package com.minh.nguyen.controller;

import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.service.MessageService;
import com.minh.nguyen.validator.MessageValidator;
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
    private MessageService messageService;

    @Autowired
    private MessageValidator validator;

    @GetMapping({"/message", "/message/"})
    public ModelAndView getMessageView() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("other/message");
        return modelAndView;
    }


    @MessageMapping("/message/send.{channelName}")
    @SendTo("/message/topic.{channelName}")
    public MessageDTO sendMessage(@DestinationVariable String channelName, MessageDTO message) {
        validator.validateMessage(message);
        return message;
    }
}

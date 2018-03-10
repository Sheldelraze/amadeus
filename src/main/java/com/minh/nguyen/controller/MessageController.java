package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.service.MessageService;
import com.minh.nguyen.validator.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private HttpSession httpSession;

    @GetMapping({"/message", "/message/"})
    public ModelAndView getMessageView() {
        ModelAndView modelAndView = createGeneralModel();
        Integer currewntUserId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        List<UserDTO> lstUser = messageService.getLstUser(null, currewntUserId, 0, Constants.MAX_USER_PER_SEARCH);
        modelAndView.addObject("lstUser", lstUser);
        modelAndView.addObject("increment", Constants.MAX_USER_PER_SEARCH);
        modelAndView.addObject("topic", Constants.DEFAULT_TOPIC);
        modelAndView.setViewName("share/message");
        return modelAndView;
    }

    @PostMapping("/message/findUser")
    public ResponseEntity<?> getSearchResultViaAjax(@RequestBody MessageDTO message) {
        Integer currewntUserId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        List<UserDTO> lstUser = messageService.getLstUser(null, currewntUserId, message.getLimitFrom(), message.getLimitTo());
        return ResponseEntity.ok(lstUser);
    }
    @MessageMapping("/message/send.{topicName}")
    @SendTo("/message/topic.{topicName}")
    public MessageDTO sendMessage(@DestinationVariable String topicName, MessageDTO message) {
        validator.validateMessage(message);
        message.setCreateTime(new Date());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        message.setSendTime(dateFormat.format(message.getCreateTime()));
        if (message.getType() == MessageDTO.MessageType.SUCCESS) {
            messageService.insertMessage(message, topicName);
        }
        return message;
    }
}

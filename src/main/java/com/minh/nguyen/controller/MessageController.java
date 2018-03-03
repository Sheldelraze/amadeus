package com.minh.nguyen.controller;

import com.minh.nguyen.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 03/03/2018
 * Purpose:
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
    @GetMapping({"/", ""})
    public ModelAndView getMessageView() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("other/message");
        return modelAndView;
    }
}

package com.minh.nguyen.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Controller
public class GeneralController {
    @GetMapping("/")
    public ModelAndView getFirst() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("share/index");
        return modelAndView;
    }
}

package com.minh.nguyen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Controller
public class GeneralController {
//    @GetMapping("/")
    public ModelAndView getFirst() {
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("submission/submission");
//        modelAndView.setViewName("contest/info/contest-role");
        modelAndView.setViewName("problem/info/problem-solution");
//        modelAndView.setViewName("share/index");
        return modelAndView;
    }
}

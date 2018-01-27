package com.minh.nguyen.controller;

import com.minh.nguyen.service.GeneralService;
import com.minh.nguyen.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Controller
public class GeneralController {
    @Autowired
    GeneralService generalService;

    @GetMapping("/")
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("share/index");
        return modelAndView;
    }
    @GetMapping("/status")
    public ModelAndView getStatus() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("share/status");
        StatusVO statusVO = new StatusVO();
        statusVO.setLstSubmission(generalService.getSubmission());
        modelAndView.addObject("statusVO",statusVO);
        return modelAndView;
    }

    @GetMapping("/submission/{snId}")
    public ModelAndView getSubmission(@PathVariable("snId") Integer snId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submission/submission");

        return modelAndView;
    }
}

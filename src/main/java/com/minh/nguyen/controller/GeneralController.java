package com.minh.nguyen.controller;

import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.service.GeneralService;
import com.minh.nguyen.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose:
 */
@Controller
public class GeneralController {

    @Autowired
    GeneralService generalService;

    @GetMapping({"/login","/login/"})
    public ModelAndView getLogin(Boolean logout) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logout",logout);
        modelAndView.setViewName("share/login");
        return modelAndView;
    }
    @GetMapping({"/",""})
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("share/index");
        return modelAndView;
    }
    @GetMapping("/403")
    public ModelAndView get403() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("share/403");
        return modelAndView;
    }
    @GetMapping("/404")
    public ModelAndView get404() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("share/404");
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
        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId);
        modelAndView.addObject("submitDetail",submissionDTO);
        return modelAndView;
    }
}

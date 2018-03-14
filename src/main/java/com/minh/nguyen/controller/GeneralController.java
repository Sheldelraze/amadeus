package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.MessageDTO;
import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.service.GeneralService;
import com.minh.nguyen.service.MessageService;
import com.minh.nguyen.vo.StatusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 01/01/2018
 * Purpose: controller for urls which anyone can access
 */
@Controller
public class GeneralController extends BaseController {

    @Autowired
    private GeneralService generalService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private MessageService messageService;

    @GetMapping({"/login", "/login/"})
    public ModelAndView getLogin(Boolean logout) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logout", logout);
        modelAndView.setViewName("share/login");
        return modelAndView;
    }

    @GetMapping({"/", ""})
    public ModelAndView getIndex() {
        ModelAndView modelAndView = createGeneralModel();
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        Object username = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_FULLNAME);
        List<MessageDTO> lstMessage = messageService.getRecentMessage(Constants.PUBLIC_TOPIC, 0, null);
        modelAndView.addObject("urId", urId);
        modelAndView.addObject(Constants.TOPIC_TEXT, Constants.PUBLIC_TOPIC);
        modelAndView.addObject("username", username);
        modelAndView.addObject("messagePerFetch", Constants.MAX_MESSAGE_PER_FETCH);
        modelAndView.addObject("lstMessage", lstMessage);
        modelAndView.setViewName("other/index");
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
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("share/status");
        StatusVO statusVO = new StatusVO();
        statusVO.setLstSubmission(generalService.getSubmission());
        modelAndView.addObject("topic", Constants.STATUS_TOPIC);
        modelAndView.addObject("statusVO", statusVO);
        return modelAndView;
    }

    @GetMapping("/submission/{snId}")
    public ModelAndView getSubmission(@PathVariable("snId") Integer snId) {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("submission/submission");
        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId);
        modelAndView.addObject("submitDetail", submissionDTO);
        return modelAndView;
    }

}

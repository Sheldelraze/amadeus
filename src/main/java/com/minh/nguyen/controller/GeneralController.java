package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.service.GeneralService;
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
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping({"/login","/login/"})
    public ModelAndView getLogin(Boolean logout) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("logout",logout);
        modelAndView.setViewName("share/login");
        return modelAndView;
    }
    @GetMapping({"/",""})
    public ModelAndView getIndex() {
        ModelAndView modelAndView = createGeneralModel();
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
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("share/status");
        StatusVO statusVO = new StatusVO();
        statusVO.setLstSubmission(generalService.getSubmission());
        modelAndView.addObject("statusVO",statusVO);
        return modelAndView;
    }

    @GetMapping("/submission/{snId}")
    public ModelAndView getSubmission(@PathVariable("snId") Integer snId){
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName("submission/submission");
        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId);
        modelAndView.addObject("submitDetail",submissionDTO);
        return modelAndView;
    }

    public ModelAndView createGeneralModel() {
        ModelAndView modelAndView = new ModelAndView();
        boolean canCreateProblem = false;
        boolean canCreateContest = false;
        if (null != httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES)) {
            List<Integer> defaultAuth = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
            if (defaultAuth.contains(Constants.AUTH_CREATE_PROBLEM_ID)) {
                canCreateProblem = true;
            }
            if (defaultAuth.contains(Constants.AUTH_CREATE_CONTEST_ID)) {
                canCreateContest = true;
            }
        }
        modelAndView.addObject("canCreateProblem", canCreateProblem);
        modelAndView.addObject("canCreateContest", canCreateContest);
        return modelAndView;
    }
}

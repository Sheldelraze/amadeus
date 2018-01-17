package com.minh.nguyen.controller;

import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.form.contest.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Controller("ContestController")
public class ContestController extends BaseController {
    public static String INFORMATION_VIEW = "contest/info/contest-information";
    public static String PROBLEM_VIEW = "contest/info/contest-problem";
    public static String SUBMIT_VIEW = "contest/info/contest-submit";
    public static String SUBMISSION_MY_VIEW = "contest/info/contest-submission-my";
    public static String SUBMISSION_ALL_VIEW = "contest/info/contest-submission-all";
    public static String LEADERBOARD_VIEW = "contest/info/contest-leaderboard";
    public static String SETTING_VIEW = "contest/info/contest-setting";
    public static String ROLE_VIEW = "contest/info/contest-role";

    public static String LAYOUT_FORM = "contestLayoutForm";
    public static String INFORMATION_FORM = "contestInformationForm";
    public static String PROBLEM_FORM = "contestProblemForm";
    public static String SUBMIT_FORM = "contestSubmitForm";
    public static String SUBMISSION_MY_FORM = "contestSubmissionMyForm";
    public static String SUBMISSION_ALL_FORM = "contestSubmissionAllForm";
    public static String LEADERBOARD_FORM = "contestLeaderboardForm";
    public static String SETTING_FORM = "contestSettingForm";
    public static String ROLE_FORM = "contestRoleForm";


    public ModelAndView getGeneralInfo(int pmId,ContestLayoutForm contestLayoutForm){
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }
    @GetMapping("/")
    public ModelAndView getFirst() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(INFORMATION_VIEW);

//        modelAndView.setViewName("share/index");
        return modelAndView;
    }

    @GetMapping("/{ctId}/information")
    public ModelAndView getInformation(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestInformationForm = new ContestInformationForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestInformationForm);
        modelAndView.setViewName(INFORMATION_VIEW);
        modelAndView.addObject(INFORMATION_FORM,contestInformationForm);

        return modelAndView;
    }

    @GetMapping("/{ctId}/problem")
    public ModelAndView getProblem(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestProblemForm = new ContestProblemForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestProblemForm);
        modelAndView.setViewName(PROBLEM_VIEW);
        modelAndView.addObject(PROBLEM_FORM,contestProblemForm);

        return modelAndView;
    }

    @GetMapping("/{ctId}/submit")
    public ModelAndView getSubmit(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestSubmitForm = new ContestSubmitForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestSubmitForm);
        modelAndView.setViewName(SUBMIT_VIEW);
        modelAndView.addObject(SUBMIT_FORM,contestSubmitForm);

        return modelAndView;
    }

    @GetMapping("/{ctId}/my")
    public ModelAndView getMy(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestSubmissionMyForm = new ContestSubmissionMyForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestSubmissionMyForm);
        modelAndView.setViewName(SUBMISSION_MY_VIEW);
        modelAndView.addObject(SUBMISSION_MY_FORM,contestSubmissionMyForm);

        return modelAndView;
    }

    @GetMapping("/{ctId}/all")
    public ModelAndView getAll(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestSubmissionAllForm = new ContestSubmissionAllForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestSubmissionAllForm);
        modelAndView.setViewName(SUBMISSION_ALL_VIEW);
        modelAndView.addObject(SUBMISSION_ALL_FORM,contestSubmissionAllForm);

        return modelAndView;
    }

    @GetMapping("/{ctId}/leaderboard")
    public ModelAndView getLeaderboard(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestLeaderboardForm = new ContestLeaderboardForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestLeaderboardForm);
        modelAndView.setViewName(LEADERBOARD_VIEW);
        modelAndView.addObject(LEADERBOARD_FORM,contestLeaderboardForm);

        return modelAndView;
    }

    @GetMapping("/{ctId}/setting")
    public ModelAndView getSetting(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestSettingForm = new ContestSettingForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestSettingForm);
        modelAndView.setViewName(SETTING_VIEW);
        modelAndView.addObject(SETTING_FORM,contestSettingForm);

        return modelAndView;
    }

    @GetMapping("/{ctId}/role")
    public ModelAndView getRole(@PathVariable("ctId") int ctId){
        ContestLayoutForm contestRoleForm = new ContestRoleForm();
        ModelAndView modelAndView = getGeneralInfo(ctId,contestRoleForm);
        modelAndView.setViewName(ROLE_VIEW);
        modelAndView.addObject(ROLE_FORM,contestRoleForm);

        return modelAndView;
    }


}


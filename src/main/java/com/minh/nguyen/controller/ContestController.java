package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.ContestDTO;
import com.minh.nguyen.dto.ProblemDTO;
import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.form.contest.*;
import com.minh.nguyen.service.ContestService;
import com.minh.nguyen.service.GeneralService;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.validator.annotation.CheckNotNullFirst;
import com.minh.nguyen.validator.annotation.CheckNotNullThird;
import com.minh.nguyen.vo.contest.ContestInformationVO;
import com.minh.nguyen.vo.contest.ContestProblemVO;
import com.minh.nguyen.vo.contest.ContestSettingVO;
import com.minh.nguyen.vo.contest.ContestSubmitVO;
import com.minh.nguyen.vo.problem.ProblemPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose:
 */
@Controller("ContestController")
@RequestMapping("/contest")
public class ContestController extends BaseController {
    private static final String INFORMATION_VIEW = "contest/info/contest-information";
    private static final String CREATE_VIEW = "contest/other/contest-create";
    private static final String PROBLEM_LIST_VIEW = "contest/info/contest-problem-list";
    private static final String PROBLEM_VIEW = "contest/info/contest-problem-view";
    private static final String ADD_PROBLEM_VIEW = "contest/other/contest-add-problem";
    private static final String SUBMIT_VIEW = "contest/info/contest-submit";
    private static final String SUBMISSION_MY_VIEW = "contest/info/contest-submission-my";
    private static final String SUBMISSION_ALL_VIEW = "contest/info/contest-submission-all";
    private static final String LEADERBOARD_VIEW = "contest/info/contest-leaderboard";
    private static final String SETTING_VIEW = "contest/info/contest-setting";
    private static final String SUBMISSION_VIEW = "submission/submission";
    private static final String ANNOUNCEMENT_VIEW = "contest/info/contest-announce";
    private static final String ROLE_VIEW = "contest/info/contest-role";
    private static final String SUBMIT_FORM = "contestSubmitForm";
    private static final String SUBMIT_VO= "contestSubmitVO";
    private static final String CREATE_FORM = "contestCreateForm";
    private static final String SUBMISSION_MY_FORM = "contestSubmissionMyForm";
    private static final String SUBMISSION_ALL_FORM = "contestSubmissionAllForm";
    private static final String SUBMISSION_LIST = "lstSubmission";
    private static final String LEADERBOARD_FORM = "contestLeaderboardForm";
    private static final String SETTING_FORM = "contestSettingForm";
    private static final String ROLE_FORM = "contestRoleForm";
    private static final String TAB = "tab";
    private static final String CONTEST_ID = "ctId";

    @Autowired
    private ContestService contestService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private GeneralService generalService;

    @PreAuthorize("hasAuthority('CAN_CREATE_CONTEST')")
    @GetMapping("/create")
    public ModelAndView createContest(ContestCreateForm contestCreateForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(CREATE_VIEW);
        if (null == contestCreateForm) {
            contestCreateForm = new ContestCreateForm();
        }
        modelAndView.addObject(CREATE_FORM, contestCreateForm);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('CAN_CREATE_CONTEST')")
    @PostMapping("/doCreate")
    public ModelAndView doCreate(ContestCreateForm contestCreateForm, BindingResult bindingResult) {
        validate(contestCreateForm,bindingResult);
        if (bindingResult.hasErrors()){
            return createContest(contestCreateForm);
        }
        try {
            ContestDTO contestDTO = new ContestDTO();
            modelMapper.map(contestCreateForm, contestDTO);
            int ctId = contestService.createContest(contestDTO);
            return getInformation(ctId);
        } catch (Exception e) {
            e.printStackTrace();
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
            return createContest(contestCreateForm);
        }
    }

    @CheckNotNullFirst
    @PreAuthorize("@ContestValidator.checkPublic(#ctId) " +
                 "|| (hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST'))")
    @GetMapping({"/{ctId}/information","/{ctId}/","/{ctId}"})
    public ModelAndView getInformation(@PathVariable("ctId") int ctId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(INFORMATION_VIEW);
        ContestInformationVO contestInformationVO = contestService.getInformation(ctId);
        modelAndView.addObject("contestInformationVO",contestInformationVO);
        modelAndView.addObject(TAB, 1);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'CAN_EDIT_CONTEST')")
    @GetMapping("/{ctId}/addProblem")
    public ModelAndView initAddProblem(@PathVariable("ctId") int ctId,ContestAddProblemForm contestAddProblemForm,
                                       Boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_PROBLEM_VIEW);
        if (null == contestAddProblemForm){
            contestAddProblemForm = new ContestAddProblemForm();
        }
        List<ProblemDTO> lstProblemDTO = contestService.getProblemToAdd(ctId);
        contestAddProblemForm.setLstProblemDTO(lstProblemDTO);
        modelAndView.addObject("updateSuccess",updateSuccess);
        modelAndView.addObject("ctId",ctId);
        modelAndView.addObject("contestAddProblemForm",contestAddProblemForm);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'CAN_EDIT_CONTEST')")
    @PostMapping("/{ctId}/addProblem")
    public ModelAndView doAddProblem(@PathVariable("ctId") int ctId,
                                     ContestAddProblemForm contestAddProblemForm,
                                     BindingResult bindingResult){
        try{
            contestService.addProblemToContest(ctId,contestAddProblemForm.getLstPmId());
        }catch (Exception e){
            e.printStackTrace();
            addLogicError(bindingResult,Constants.MSG_SYSTEM_ERR);
        }
        if (bindingResult.hasErrors()){
            return initAddProblem(ctId,contestAddProblemForm,false);
        }
        return initAddProblem(ctId,contestAddProblemForm,true);
    }

    @CheckNotNullFirst
    @CheckNotNullThird
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'CAN_EDIT_CONTEST')")
    @GetMapping("/{ctId}/showProblem/{pmId}")
    public ModelAndView showProblem(@PathVariable("ctId") int ctId,@PathVariable("pmId") int pmId) {
        contestService.setProblemHiddenStatus(ctId,pmId,Constants.STATUS_SHOW);
        return getProblem(ctId);
    }

    @CheckNotNullFirst
    @CheckNotNullThird
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'CAN_EDIT_CONTEST')")
    @GetMapping("/{ctId}/hideProblem/{pmId}")
    public ModelAndView hideProblem(@PathVariable("ctId") int ctId,@PathVariable("pmId") int pmId) {
        contestService.setProblemHiddenStatus(ctId,pmId,Constants.STATUS_HIDDEN);
        return getProblem(ctId);
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/problem")
    public ModelAndView getProblem(@PathVariable("ctId") int ctId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PROBLEM_LIST_VIEW);
        List<ProblemDTO> lstProblemDTO = contestService.getProblemToDisplay(ctId);
        ContestProblemVO contestProblemVO = new ContestProblemVO();
        contestProblemVO.setLstProblemDTO(lstProblemDTO);
        modelAndView.addObject(TAB, 2);
        modelAndView.addObject("contestProblemVO", contestProblemVO);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/problem/{pmId}/view")
    public ModelAndView viewProblem(@PathVariable("ctId") int ctId,@PathVariable("pmId") int pmId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(PROBLEM_VIEW);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        problemService.getShowInStatementTest(problemDTO);
        ProblemPreviewVO problemPreviewVO = new ProblemPreviewVO();
        modelMapper.map(problemDTO, problemPreviewVO);
        modelAndView.addObject(TAB, 2);
        modelAndView.addObject("problemVO", problemPreviewVO);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId)")
    @GetMapping("/{ctId}/submit")
    public ModelAndView getSubmit(@PathVariable("ctId") int ctId,ContestSubmitForm contestSubmitForm) {
        ModelAndView modelAndView = new ModelAndView();
        ContestSubmitVO contestSubmitVO = new ContestSubmitVO();
        contestSubmitVO.setLstLanguage(problemService.getAllLanguage());
        contestSubmitVO.setLstProblem(contestService.getProblemToSubmit(ctId));
        if(contestSubmitForm.getId() != null){
            contestSubmitVO.setSourceCode(contestSubmitForm.getSourceCode());
        }else{
            contestSubmitForm = new ContestSubmitForm();
        }
        modelAndView.setViewName(SUBMIT_VIEW);
        modelAndView.addObject(SUBMIT_FORM, contestSubmitForm);
        modelAndView.addObject(SUBMIT_VO, contestSubmitVO);
        modelAndView.addObject(TAB, 3);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkParticipate(authentication,#ctId)")
    @PostMapping("/{ctId}/submit")
    public ModelAndView doSubmit(@PathVariable("ctId") int ctId,ContestSubmitForm contestSubmitForm,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        contestSubmitForm.setScreenName("submitForm");
        validate(contestSubmitForm,bindingResult);
        if(bindingResult.hasErrors()){
            contestSubmitForm.setId(ctId);
            return getSubmit(ctId,contestSubmitForm);
        }
        contestService.doSubmit(contestSubmitForm.getSourceCode(),ctId,
                contestSubmitForm.getLeId(),
                contestSubmitForm.getPmId());
        modelAndView.setViewName("redirect:/contest/" + ctId + "/my");
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkParticipate(authentication,#ctId)")
    @GetMapping("/{ctId}/my")
    public ModelAndView getMy(@PathVariable("ctId") int ctId) {
        ContestLayoutForm contestSubmissionMyForm = new ContestSubmissionMyForm();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SUBMISSION_MY_VIEW);
        modelAndView.addObject(SUBMISSION_MY_FORM, contestSubmissionMyForm);
        List<SubmissionDTO> lstSubmission = contestService.getSubmissionInContest(ctId,false);
        modelAndView.addObject(TAB, 4);
        modelAndView.addObject(CONTEST_ID, ctId);
        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) ")
    @GetMapping("/{ctId}/all")
    public ModelAndView getAll(@PathVariable("ctId") int ctId) {
        ContestLayoutForm contestSubmissionAllForm = new ContestSubmissionAllForm();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SUBMISSION_ALL_VIEW);
        modelAndView.addObject(SUBMISSION_ALL_FORM, contestSubmissionAllForm);
        List<SubmissionDTO> lstSubmission = contestService.getSubmissionInContest(ctId,true);
        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
        modelAndView.addObject(TAB, 5);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/leaderboard")
    public ModelAndView getLeaderboard(@PathVariable("ctId") int ctId) {
        ContestLayoutForm contestLeaderboardForm = new ContestLeaderboardForm();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LEADERBOARD_VIEW);
        modelAndView.addObject(LEADERBOARD_FORM, contestLeaderboardForm);
        modelAndView.addObject(TAB, 6);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/announcement")
    public ModelAndView getAnnouncement(@PathVariable("ctId") int ctId) {
        ContestLayoutForm contestSettingForm = new ContestSettingForm();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ANNOUNCEMENT_VIEW);

        modelAndView.addObject(TAB, 7);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('CAN_VIEW_ALL_CONTEST') || @ContestValidator.checkPermission(authentication,#ctId,'CAN_VIEW_CONTEST') " +
            "|| @ContestValidator.checkShowSubmitPolicy(#ctId,#snId)")
    @GetMapping("/{ctId}/submission/{snId}")
    public ModelAndView getSubmission(@PathVariable("ctId") int ctId,@PathVariable("snId") int snId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SUBMISSION_VIEW);
        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId,ctId);
        modelAndView.addObject("submitDetail",submissionDTO);
        modelAndView.addObject(TAB, 0);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'CAN_EDIT_CONTEST') ")
    @GetMapping("/{ctId}/setting")
    public ModelAndView getSetting(@PathVariable("ctId") int ctId,ContestSettingForm contestSettingForm,boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SETTING_VIEW);
        ContestSettingVO contestSettingVO = new ContestSettingVO();
        if (null == contestSettingForm.getId()){
            contestSettingForm = new ContestSettingForm();
            ContestDTO contestDTO = contestService.getContestInfo(ctId);
            contestService.modelMapper.map(contestDTO,contestSettingVO);
        }
        else{
            contestService.modelMapper.map(contestSettingForm,contestSettingVO);
        }
        modelAndView.addObject("updateSuccess",updateSuccess);
        modelAndView.addObject(SETTING_FORM, contestSettingForm);
        modelAndView.addObject(TAB, 8);
        modelAndView.addObject(CONTEST_ID, ctId);
        modelAndView.addObject("contestSettingVO",contestSettingVO);
        modelAndView.addObject("contestSettingForm",contestSettingForm);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'CAN_EDIT_CONTEST') ")
    @PostMapping("/{ctId}/setting")
    public ModelAndView updateSetting(@PathVariable("ctId") int ctId,ContestSettingForm contestSettingForm,BindingResult bindingResult){
        validate(contestSettingForm,bindingResult);
        if(bindingResult.hasErrors()){
            contestSettingForm.setId(ctId);
            return  getSetting(ctId,contestSettingForm,false);
        }
        try{
            contestSettingForm.setId(ctId);
            contestService.updateContest(contestSettingForm);
        }catch(Exception e){
            e.printStackTrace();
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }
        return  getSetting(ctId,contestSettingForm,true);
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkCreator(authentication,#ctId)")
    @GetMapping("/{ctId}/role")
    public ModelAndView getRole(@PathVariable("ctId") int ctId) {
        ContestLayoutForm contestRoleForm = new ContestRoleForm();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ROLE_VIEW);
        modelAndView.addObject(ROLE_FORM, contestRoleForm);
        modelAndView.addObject(TAB, 9);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }


}


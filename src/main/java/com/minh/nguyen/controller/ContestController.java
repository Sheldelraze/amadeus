package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
import com.minh.nguyen.form.contest.*;
import com.minh.nguyen.service.ContestService;
import com.minh.nguyen.service.GeneralService;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.ContestValidator;
import com.minh.nguyen.validator.annotation.CheckNotNullFirst;
import com.minh.nguyen.validator.annotation.CheckNotNullThird;
import com.minh.nguyen.vo.contest.ContestInformationVO;
import com.minh.nguyen.vo.contest.ContestProblemVO;
import com.minh.nguyen.vo.contest.ContestSettingVO;
import com.minh.nguyen.vo.contest.ContestSubmitVO;
import com.minh.nguyen.vo.problem.ProblemPreviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/01/2018
 * Purpose: controller for contest
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
    private static final String ANNOUNCEMENT_VIEW = "contest/info/contest-announcement";
    private static final String ADD_ROLE_VIEW = "contest/other/contest-add-role";
    private static final String ROLE_VIEW = "contest/info/contest-role";
    private static final String SUBMIT_FORM = "contestSubmitForm";
    private static final String SUBMIT_VO = "contestSubmitVO";
    private static final String CREATE_FORM = "contestCreateForm";
    private static final String SUBMISSION_MY_FORM = "contestSubmissionMyForm";
    private static final String SUBMISSION_ALL_FORM = "contestSubmissionAllForm";
    private static final String SUBMISSION_LIST = "lstSubmission";
    private static final String LEADERBOARD_FORM = "contestLeaderboardForm";
    private static final String SETTING_FORM = "contestSettingForm";
    private static final String ROLE_FORM = "contestRoleForm";
    private static final String TAB = "tab";
    private static final String CONTEST_ID = "ctId";
    private static final String UPDATE_SUCCESS = "updateSuccess";
    private static final String AUTHORITY = "contestAuth";

    @Autowired
    private ContestService contestService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private GeneralService generalService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ContestValidator contestValidator;

    @Autowired
    private GeneralController generalController;

    private ModelAndView createGeneralModel(int ctId) {

        //add common information
        ModelAndView modelAndView = generalController.createGeneralModel();
        ContestDTO contestDTO = contestService.getContestTime(ctId);
        modelAndView.addObject("startTime", contestDTO.getStartTime());
        modelAndView.addObject("endTime", contestDTO.getEndTime());
        modelAndView.addObject("doUpdate", contestDTO.getDoUpdateCountDown());
        modelAndView.addObject("timerMessage", contestDTO.getTimerMessage());
        modelAndView.addObject("contestName", contestDTO.getName());

        //add authority of current user (CAN_VIEW and CAN_PARTICIPATE only)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (contestValidator.checkPermission(auth, ctId, Constants.AUTH_EDIT_CONTEST_TEXT)) {
                modelAndView.addObject(AUTHORITY, Constants.AUTH_EDIT_CONTEST_TEXT);
            } else if (contestValidator.checkPermission(auth, ctId, Constants.AUTH_PARTICIPATE_CONTEST_TEXT)) {
                modelAndView.addObject(AUTHORITY, Constants.AUTH_PARTICIPATE_CONTEST_TEXT);
            } else {
                modelAndView.addObject(AUTHORITY, "No special authority");
            }
        }

        //check if user can view common status
        boolean canViewStatus = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            List<Integer> defaultAuth = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
            if (null != defaultAuth && defaultAuth.contains(Constants.AUTH_VIEW_ALL_CONTEST_ID)) {
                canViewStatus = true;
            } else if (contestValidator.checkPermission(auth, ctId, Constants.AUTH_VIEW_CONTEST_TEXT)) {
                canViewStatus = true;
            } else {
                if (contestValidator.checkParticipate(auth, ctId) && contestValidator.canViewStatus(ctId)) {
                    canViewStatus = true;
                } else if (contestValidator.checkOutsiderPermission(auth, ctId)) {
                    canViewStatus = true;
                }
            }
        }
        modelAndView.addObject("canViewStatus", canViewStatus);

        //add contest's creator check
        boolean isCreator = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (contestValidator.checkCreator(auth, ctId)) {
                isCreator = true;
            }
        }
        modelAndView.addObject("isCreator", isCreator);

        //add announcement count if any
        Integer atCnt = contestService.getAnnouncementCount(ctId);
        if (atCnt > 0) {
            modelAndView.addObject("atCnt", atCnt);
        }
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_CONTEST_TEXT + "')")
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

    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_CONTEST_TEXT + "')")
    @PostMapping("/doCreate")
    public ModelAndView doCreate(ContestCreateForm contestCreateForm, BindingResult bindingResult) {
        validate(contestCreateForm, bindingResult);
        if (bindingResult.hasErrors()) {
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
            "|| (hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') " +
            "|| @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "'))")
    @GetMapping({"/{ctId}/information", "/{ctId}/", "/{ctId}"})
    public ModelAndView getInformation(@PathVariable("ctId") int ctId) {
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(INFORMATION_VIEW);
        ContestInformationVO contestInformationVO = contestService.getInformation(ctId);
        modelAndView.addObject("contestInformationVO", contestInformationVO);
        modelAndView.addObject(TAB, 1);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "')")
    @GetMapping("/{ctId}/addProblem")
    public ModelAndView initAddProblem(@PathVariable("ctId") int ctId, ContestAddProblemForm contestAddProblemForm,
                                       Boolean updateSuccess) {
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(ADD_PROBLEM_VIEW);
        if (null == contestAddProblemForm) {
            contestAddProblemForm = new ContestAddProblemForm();
        }
        List<ProblemDTO> lstProblemDTO = contestService.getProblemToAdd(ctId);
        contestAddProblemForm.setLstProblemDTO(lstProblemDTO);
        modelAndView.addObject("updateSuccess", updateSuccess);
        modelAndView.addObject("ctId", ctId);
        modelAndView.addObject("contestAddProblemForm", contestAddProblemForm);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "')")
    @PostMapping("/{ctId}/addProblem")
    public ModelAndView doAddProblem(@PathVariable("ctId") int ctId,
                                     ContestAddProblemForm contestAddProblemForm,
                                     BindingResult bindingResult) {
        try {
            contestService.addProblemToContest(ctId, contestAddProblemForm.getLstPmId());
        } catch (Exception e) {
            e.printStackTrace();
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR);
        }
        if (bindingResult.hasErrors()) {
            return initAddProblem(ctId, contestAddProblemForm, false);
        }
        return initAddProblem(ctId, contestAddProblemForm, true);
    }

    @CheckNotNullFirst
    @CheckNotNullThird
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "')")
    @GetMapping("/{ctId}/showProblem/{pmId}")
    public ModelAndView showProblem(@PathVariable("ctId") int ctId, @PathVariable("pmId") int pmId) {
        contestService.setProblemHiddenStatus(ctId, pmId, Constants.STATUS_SHOW);
        return getProblem(ctId);
    }

    @CheckNotNullFirst
    @CheckNotNullThird
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "')")
    @GetMapping("/{ctId}/hideProblem/{pmId}")
    public ModelAndView hideProblem(@PathVariable("ctId") int ctId, @PathVariable("pmId") int pmId) {
        contestService.setProblemHiddenStatus(ctId, pmId, Constants.STATUS_HIDDEN);
        return getProblem(ctId);
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') || @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/problem")
    public ModelAndView getProblem(@PathVariable("ctId") int ctId) {
        ModelAndView modelAndView = createGeneralModel(ctId);
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
    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') || @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/problem/{pmId}/view")
    public ModelAndView viewProblem(@PathVariable("ctId") int ctId, @PathVariable("pmId") int pmId) {
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(PROBLEM_VIEW);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        problemService.getShowInStatementTest(problemDTO);
        ProblemPreviewVO problemPreviewVO = new ProblemPreviewVO();
        if (null != problemPreviewVO.getNote() && StringUtil.checkBlank(problemPreviewVO.getNote())) {
            problemPreviewVO.setNote(null);
        }
        modelMapper.map(problemDTO, problemPreviewVO);
        modelAndView.addObject(TAB, 2);
        modelAndView.addObject("problemVO", problemPreviewVO);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') || @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId)")
    @GetMapping("/{ctId}/submit")
    public ModelAndView getSubmit(@PathVariable("ctId") int ctId, ContestSubmitForm contestSubmitForm) {
        ModelAndView modelAndView = createGeneralModel(ctId);
        ContestSubmitVO contestSubmitVO = new ContestSubmitVO();
        contestSubmitVO.setLstLanguage(problemService.getAllLanguage());
        contestSubmitVO.setLstProblem(contestService.getProblemToSubmit(ctId));
        if (contestSubmitForm.getId() != null) {
            contestSubmitVO.setSourceCode(contestSubmitForm.getSourceCode());
        } else {
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
    public ModelAndView doSubmit(@PathVariable("ctId") int ctId, ContestSubmitForm contestSubmitForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        contestSubmitForm.setScreenName("submitForm");
        validate(contestSubmitForm, bindingResult);
        if (bindingResult.hasErrors()) {
            contestSubmitForm.setId(ctId);
            return getSubmit(ctId, contestSubmitForm);
        }
        contestService.doSubmit(contestSubmitForm.getSourceCode(), ctId,
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
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(SUBMISSION_MY_VIEW);
        modelAndView.addObject(SUBMISSION_MY_FORM, contestSubmissionMyForm);
        List<SubmissionDTO> lstSubmission = contestService.getSubmissionInContest(ctId, false);
        modelAndView.addObject(TAB, 4);
        modelAndView.addObject(CONTEST_ID, ctId);
        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') || @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "') " +
            "|| (@ContestValidator.checkParticipate(authentication,#ctId) && @ContestValidator.canViewStatus(#ctId))")
    @GetMapping("/{ctId}/all")
    public ModelAndView getAll(@PathVariable("ctId") int ctId) {
        ContestLayoutForm contestSubmissionAllForm = new ContestSubmissionAllForm();
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(SUBMISSION_ALL_VIEW);
        modelAndView.addObject(SUBMISSION_ALL_FORM, contestSubmissionAllForm);
        List<SubmissionDTO> lstSubmission = contestService.getSubmissionInContest(ctId, true);
        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
        modelAndView.addObject(TAB, 5);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') || @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "') " +
            "|| (@ContestValidator.checkParticipate(authentication,#ctId) && @ContestValidator.canViewStatus(#ctId)) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/leaderboard")
    public ModelAndView getLeaderboard(@PathVariable("ctId") int ctId) {
        ContestLayoutForm contestLeaderboardForm = new ContestLeaderboardForm();
        ModelAndView modelAndView = createGeneralModel(ctId);
        List<UserDTO> lstUser = contestService.getLeaderboardInfor(ctId);
        List<ProblemDTO> lstProblem = contestService.getProblemForLeaderboard(ctId);
        modelAndView.addObject("lstUser", lstUser);
        modelAndView.addObject("lstProb", lstProblem);
        modelAndView.setViewName(LEADERBOARD_VIEW);
        modelAndView.addObject(LEADERBOARD_FORM, contestLeaderboardForm);
        modelAndView.addObject(TAB, 6);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') || @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "') " +
            "|| @ContestValidator.checkParticipate(authentication,#ctId) " +
            "|| @ContestValidator.checkOutsiderPermission(authentication,#ctId)")
    @GetMapping("/{ctId}/announcement")
    public ModelAndView getAnnouncement(@PathVariable("ctId") int ctId, ContestAnnouncementForm contestAnnouncementForm, boolean updateSuccess) {
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(ANNOUNCEMENT_VIEW);
        List<ProblemDTO> lstProblem = contestService.getProblemToSubmit(ctId);
        if (null == contestAnnouncementForm) {
            contestAnnouncementForm = new ContestAnnouncementForm();
        }
        List<AnnouncementDTO> lstAnnounce = contestService.getAnnouncementList(ctId);
        modelAndView.addObject("contestAnnouncementForm", contestAnnouncementForm);
        modelAndView.addObject("lstProblem", lstProblem);
        modelAndView.addObject("lstAnnounce", lstAnnounce);
        modelAndView.addObject(TAB, 7);
        modelAndView.addObject(CONTEST_ID, ctId);
        modelAndView.addObject("updateSuccess", updateSuccess);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkParticipate(authentication,#ctId)")
    @PostMapping("/{ctId}/addQuestion")
    public ModelAndView addQuestion(@PathVariable("ctId") int ctId, ContestAnnouncementForm contestAnnouncementForm, BindingResult bindingResult) {
        validate(contestAnnouncementForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return getAnnouncement(ctId, contestAnnouncementForm, false);
        }
        contestService.addQuestion(ctId, Integer.parseInt(contestAnnouncementForm.getPmId()), contestAnnouncementForm.getQuestion());
        return getAnnouncement(ctId, contestAnnouncementForm, true);
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "')")
    @PostMapping("/{ctId}/addAnnouncement")
    public ModelAndView addAnnouncement(@PathVariable("ctId") int ctId, ContestAnnouncementForm contestAnnouncementForm, BindingResult bindingResult) {
        validate(contestAnnouncementForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return getAnnouncement(ctId, contestAnnouncementForm, false);
        }
        contestService.addAnnouncement(ctId, Integer.parseInt(contestAnnouncementForm.getPmId()), contestAnnouncementForm.getAnswer());
        return getAnnouncement(ctId, contestAnnouncementForm, true);
    }

    @CheckNotNullFirst
    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_CONTEST_TEXT + "') || @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_CONTEST_TEXT + "') " +
            "|| @ContestValidator.checkShowSubmitPolicy(#ctId,#snId)")
    @GetMapping("/{ctId}/submission/{snId}")
    public ModelAndView getSubmission(@PathVariable("ctId") int ctId, @PathVariable("snId") int snId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(SUBMISSION_VIEW);
        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId, ctId);
        modelAndView.addObject("submitDetail", submissionDTO);
        modelAndView.addObject(TAB, 0);
        modelAndView.addObject(CONTEST_ID, ctId);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "') ")
    @GetMapping("/{ctId}/setting")
    public ModelAndView getSetting(@PathVariable("ctId") int ctId, ContestSettingForm contestSettingForm, boolean updateSuccess) {
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(SETTING_VIEW);
        ContestSettingVO contestSettingVO = new ContestSettingVO();
        if (null == contestSettingForm.getId()) {
            contestSettingForm = new ContestSettingForm();
            ContestDTO contestDTO = contestService.getContestInfo(ctId);
            contestService.modelMapper.map(contestDTO, contestSettingVO);
        } else {
            contestService.modelMapper.map(contestSettingForm, contestSettingVO);
        }
        modelAndView.addObject("updateSuccess", updateSuccess);
        modelAndView.addObject(SETTING_FORM, contestSettingForm);
        modelAndView.addObject(TAB, 8);
        modelAndView.addObject(CONTEST_ID, ctId);
        modelAndView.addObject("contestSettingVO", contestSettingVO);
        modelAndView.addObject("contestSettingForm", contestSettingForm);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "') ")
    @PostMapping("/{ctId}/setting")
    public ModelAndView updateSetting(@PathVariable("ctId") int ctId, ContestSettingForm contestSettingForm, BindingResult bindingResult) {
        validate(contestSettingForm, bindingResult);
        if (bindingResult.hasErrors()) {
            contestSettingForm.setId(ctId);
            return getSetting(ctId, contestSettingForm, false);
        }
        try {
            contestSettingForm.setId(ctId);
            contestService.updateContest(contestSettingForm);
        } catch (Exception e) {
            e.printStackTrace();
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
        }
        return getSetting(ctId, contestSettingForm, true);
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkCreator(authentication,#ctId)")
    @GetMapping("/{ctId}/role")
    public ModelAndView getRole(@PathVariable("ctId") int ctId, boolean updateSuccess) {
        ContestLayoutForm contestRoleForm = new ContestRoleForm();
        ModelAndView modelAndView = createGeneralModel(ctId);
        modelAndView.setViewName(ROLE_VIEW);
        modelAndView.addObject(ROLE_FORM, contestRoleForm);
        modelAndView.addObject(TAB, 9);
        modelAndView.addObject(CONTEST_ID, ctId);
        List<UserDTO> lstUser = contestService.getListContestRole(ctId);
        Integer currentUrId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        String currentUrName = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_FULLNAME);
        String currentUrHandle = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        Integer currentReId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_ID);
        String currentReName = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_NAME);
        modelAndView.addObject("lstUser", lstUser);
        modelAndView.addObject("currentUrId", currentUrId);
        modelAndView.addObject("currentUrName", currentUrName);
        modelAndView.addObject("currentReId", currentReId);
        modelAndView.addObject("currentUrHandle", currentUrHandle);
        modelAndView.addObject("currentReName", currentReName);
        if (updateSuccess) {
            modelAndView.addObject(UPDATE_SUCCESS, true);
        }
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkCreator(authentication,#ctId)")
    @GetMapping("/{ctId}/addRole")
    public ModelAndView getAddRole(@PathVariable("ctId") Integer ctId, ContestAddRoleForm contestAddRoleForm,
                                   boolean updateSuccess) {
        ModelAndView modelAndView = new ModelAndView();
        if (null == contestAddRoleForm || null == contestAddRoleForm.getId()) {
            contestAddRoleForm = new ContestAddRoleForm();
        }
        modelAndView.setViewName(ADD_ROLE_VIEW);
        modelAndView.addObject("ctId", ctId);
        modelAndView.addObject("contestAddRoleForm", contestAddRoleForm);
        if (updateSuccess) {
            modelAndView.addObject(UPDATE_SUCCESS, true);
        }
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkCreator(authentication,#ctId)")
    @PostMapping("/{ctId}/findForRole")
    public ModelAndView findForAddRole(@PathVariable("ctId") Integer ctId, ContestAddRoleForm contestAddRoleForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_ROLE_VIEW);
        List<UserDTO> lstUser = contestService.findUserForContestRole(contestAddRoleForm.getFullname(), Integer.parseInt(contestAddRoleForm.getReId()), ctId);
        modelAndView.addObject("lstUser", lstUser);
        return modelAndView;
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkCreator(authentication,#ctId)")
    @PostMapping("/{ctId}/addRole")
    public ModelAndView addRole(@PathVariable("ctId") Integer ctId, ContestAddRoleForm contestAddRoleForm) throws UserTryingToBeSmartException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADD_ROLE_VIEW);
        contestService.addRole(contestAddRoleForm.getLstUrId(), Integer.parseInt(contestAddRoleForm.getAuyId()), ctId);
        return getAddRole(ctId, contestAddRoleForm, true);
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkCreator(authentication,#ctId)")
    @GetMapping("/{ctId}/deleteRole/{urId}")
    public ModelAndView deleteRole(@PathVariable("ctId") Integer ctId, @PathVariable("urId") Integer urId) throws UserTryingToBeSmartException {
        Integer currentUrId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        if (currentUrId.equals(urId)) {
            throw new UserTryingToBeSmartException();
        }
        contestService.deleteRole(ctId, urId);
        return getRole(ctId, true);
    }

    @CheckNotNullFirst
    @PreAuthorize("isAuthenticated() && @ContestValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_CONTEST_TEXT + "')")
    @GetMapping("/{ctId}/changeAnnounceState/{atId}/to/{newState}")
    public ModelAndView changeAnnounceState(@PathVariable("ctId") Integer ctId, @PathVariable("atId") Integer atId, @PathVariable("newState") Integer newState) {
        contestService.changeAnnounceHiddenState(atId, newState);
        return getAnnouncement(ctId, null, true);
    }


}


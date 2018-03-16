package com.minh.nguyen.controller;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.controller.common.BaseController;
import com.minh.nguyen.dto.CourseDTO;
import com.minh.nguyen.form.course.CourseCreateForm;
import com.minh.nguyen.service.CourseService;
import com.minh.nguyen.service.GeneralService;
import com.minh.nguyen.service.ProblemService;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.CourseValidator;
import com.minh.nguyen.validator.annotation.CheckNotNullFirst;
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
 * @since 14/03/2018
 * Purpose:
 */
@Controller("CourseController")
@RequestMapping("/course")
public class CourseController extends BaseController {

    private static final String INFORMATION_VIEW = "course/info/course-information";
    private static final String CREATE_VIEW = "course/other/course-create";
    private static final String PROBLEM_LIST_VIEW = "course/info/course-problem-list";
    private static final String PROBLEM_VIEW = "course/info/course-problem-view";
    private static final String ADD_PROBLEM_VIEW = "course/other/course-add-problem";
    private static final String SUBMIT_VIEW = "course/info/course-submit";
    private static final String SUBMISSION_MY_VIEW = "course/info/course-submission-my";
    private static final String SUBMISSION_ALL_VIEW = "course/info/course-submission-all";
    private static final String LEADERBOARD_VIEW = "course/info/course-leaderboard";
    private static final String SETTING_VIEW = "course/info/course-setting";
    private static final String ANSWER_VIEW = "course/other/course-answer";
    private static final String LIST_ALL_VIEW = "course/list/course-list-all";
    private static final String SUBMISSION_VIEW = "submission/submission";
    private static final String ANNOUNCEMENT_VIEW = "course/info/course-announcement";
    private static final String ADD_ROLE_VIEW = "course/other/course-add-role";
    private static final String ROLE_VIEW = "course/info/course-role";
    private static final String SUBMIT_FORM = "courseSubmitForm";
    private static final String SUBMIT_VO = "courseSubmitVO";
    private static final String CREATE_FORM = "courseCreateForm";
    private static final String SUBMISSION_MY_FORM = "courseSubmissionMyForm";
    private static final String SUBMISSION_ALL_FORM = "courseSubmissionAllForm";
    private static final String SUBMISSION_LIST = "lstSubmission";
    private static final String LEADERBOARD_FORM = "courseLeaderboardForm";
    private static final String SETTING_FORM = "courseSettingForm";
    private static final String ROLE_FORM = "courseRoleForm";
    private static final String COURSE_ID = "ceId";
    private static final String AUTHORITY = "courseAuth";

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private GeneralService generalService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CourseValidator courseValidator;

    private ModelAndView createGeneralModel(int ceId) {

        //add common information
        ModelAndView modelAndView = createGeneralModel();

        //add authority of current user (CAN_VIEW and CAN_PARTICIPATE only)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (courseValidator.checkPermission(auth, ceId, Constants.AUTH_EDIT_COURSE_TEXT)) {
                modelAndView.addObject(AUTHORITY, Constants.AUTH_EDIT_COURSE_TEXT);
            } else if (courseValidator.checkPermission(auth, ceId, Constants.AUTH_PARTICIPATE_COURSE_TEXT)) {
                modelAndView.addObject(AUTHORITY, Constants.AUTH_PARTICIPATE_COURSE_TEXT);
            } else {
                modelAndView.addObject(AUTHORITY, "No special authority");
            }
        }

        //check if user can view common status
        boolean canViewStatus = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            List<Integer> defaultAuth = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
            if (null != defaultAuth && defaultAuth.contains(Constants.AUTH_VIEW_ALL_COURSE_ID)) {
                canViewStatus = true;
            } else if (courseValidator.checkPermission(auth, ceId, Constants.AUTH_VIEW_COURSE_TEXT)) {
                canViewStatus = true;
            } else {
                if (courseValidator.checkParticipate(auth, ceId)) {
                    canViewStatus = true;
                }
            }
        }
        modelAndView.addObject("canViewStatus", canViewStatus);

        //add course's creator check
        boolean isCreator = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (courseValidator.checkCreator(auth, ceId)) {
                isCreator = true;
            }
        }
        modelAndView.addObject("isCreator", isCreator);

        //add announcement count if any
        Integer atCnt = courseService.getAnnouncementCount(ceId);
        if (atCnt > 0) {
            modelAndView.addObject("atCnt", atCnt);
        }
        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAllCourse() {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName(LIST_ALL_VIEW);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_COURSE_TEXT + "')")
    @GetMapping("/create")
    public ModelAndView createCourse(CourseCreateForm courseCreateForm) {
        ModelAndView modelAndView = createGeneralModel();
        modelAndView.setViewName(CREATE_VIEW);
        if (null == courseCreateForm) {
            courseCreateForm = new CourseCreateForm();
        }
        modelAndView.addObject(CREATE_FORM, courseCreateForm);
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_COURSE_TEXT + "')")
    @PostMapping("/doCreate")
    public ModelAndView doCreate(CourseCreateForm courseCreateForm, BindingResult bindingResult) {
        validate(courseCreateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return createCourse(courseCreateForm);
        }
        try {
            CourseDTO courseDTO = new CourseDTO();
            modelMapper.map(courseCreateForm, courseDTO);
            int ceId = courseService.createCourse(courseDTO);
            return getInformation(ceId);
        } catch (Exception e) {
            e.printStackTrace();
            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
            return createCourse(courseCreateForm);
        }
    }

    @CheckNotNullFirst
    @GetMapping({"/{ceId}/information", "/{ceId}/", "/{ceId}"})
    public ModelAndView getInformation(@PathVariable("ceId") int ceId) {
        ModelAndView modelAndView = createGeneralModel(ceId);
        modelAndView.setViewName(INFORMATION_VIEW);
        CourseDTO courseDTO = courseService.getInformation(ceId);
        modelAndView.addObject("course", courseDTO);
        modelAndView.addObject(TAB, 1);
        modelAndView.addObject(COURSE_ID, ceId);
        return modelAndView;
    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @GetMapping("/{ceId}/addProblem")
//    public ModelAndView initAddProblem(@PathVariable("ceId") int ceId, CourseAddProblemForm courseAddProblemForm,
//                                       Boolean updateSuccess) {
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(ADD_PROBLEM_VIEW);
//        if (null == courseAddProblemForm) {
//            courseAddProblemForm = new CourseAddProblemForm();
//        }
//        List<ProblemDTO> lstProblemDTO = courseService.getProblemToAdd(ceId);
//        courseAddProblemForm.setLstProblemDTO(lstProblemDTO);
//        modelAndView.addObject("updateSuccess", updateSuccess);
//        modelAndView.addObject("ceId", ceId);
//        modelAndView.addObject("courseAddProblemForm", courseAddProblemForm);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @PostMapping("/{ceId}/addProblem")
//    public ModelAndView doAddProblem(@PathVariable("ceId") int ceId,
//                                     CourseAddProblemForm courseAddProblemForm,
//                                     BindingResult bindingResult) {
//        try {
//            courseService.addProblemToCourse(ceId, courseAddProblemForm.getLstPmId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR);
//        }
//        if (bindingResult.hasErrors()) {
//            return initAddProblem(ceId, courseAddProblemForm, false);
//        }
//        return initAddProblem(ceId, courseAddProblemForm, true);
//    }
//
//    @CheckNotNullFirst
//    @CheckNotNullThird
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @GetMapping("/{ceId}/showProblem/{pmId}")
//    public ModelAndView showProblem(@PathVariable("ceId") int ceId, @PathVariable("pmId") int pmId) {
//        courseService.setProblemHiddenStatus(ceId, pmId, Constants.STATUS_SHOW);
//        return getProblem(ceId);
//    }
//
//    @CheckNotNullFirst
//    @CheckNotNullThird
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @GetMapping("/{ceId}/hideProblem/{pmId}")
//    public ModelAndView hideProblem(@PathVariable("ceId") int ceId, @PathVariable("pmId") int pmId) {
//        courseService.setProblemHiddenStatus(ceId, pmId, Constants.STATUS_HIDDEN);
//        return getProblem(ceId);
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
//            "|| @CourseValidator.checkParticipate(authentication,#ceId) " +
//            "|| @CourseValidator.checkOutsiderPermission(authentication,#ceId)")
//    @GetMapping("/{ceId}/problem")
//    public ModelAndView getProblem(@PathVariable("ceId") int ceId) {
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(PROBLEM_LIST_VIEW);
//        List<ProblemDTO> lstProblemDTO = courseService.getProblemToDisplay(ceId);
//        CourseProblemVO courseProblemVO = new CourseProblemVO();
//        courseProblemVO.setLstProblemDTO(lstProblemDTO);
//        modelAndView.addObject(TAB, 2);
//        modelAndView.addObject("courseProblemVO", courseProblemVO);
//        modelAndView.addObject(COURSE_ID, ceId);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
//            "|| @CourseValidator.checkParticipate(authentication,#ceId) " +
//            "|| @CourseValidator.checkOutsiderPermission(authentication,#ceId)")
//    @GetMapping("/{ceId}/problem/{pmId}/view")
//    public ModelAndView viewProblem(@PathVariable("ceId") int ceId, @PathVariable("pmId") int pmId) {
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(PROBLEM_VIEW);
//        ProblemDTO problemDTO = new ProblemDTO();
//        problemDTO.setId(pmId);
//        problemService.getProblemInfo(problemDTO);
//        problemService.getShowInStatementTest(problemDTO);
//        ProblemPreviewVO problemPreviewVO = new ProblemPreviewVO();
//        if (null != problemPreviewVO.getNote() && StringUtil.checkBlank(problemPreviewVO.getNote())) {
//            problemPreviewVO.setNote(null);
//        }
//        modelMapper.map(problemDTO, problemPreviewVO);
//        modelAndView.addObject(TAB, 2);
//        modelAndView.addObject("problemVO", problemPreviewVO);
//        modelAndView.addObject(COURSE_ID, ceId);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
//            "|| @CourseValidator.checkParticipate(authentication,#ceId)")
//    @GetMapping("/{ceId}/submit")
//    public ModelAndView getSubmit(@PathVariable("ceId") int ceId, CourseSubmitForm courseSubmitForm) {
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        CourseSubmitVO courseSubmitVO = new CourseSubmitVO();
//        courseSubmitVO.setLstLanguage(problemService.getAllLanguage());
//        courseSubmitVO.setLstProblem(courseService.getProblemToSubmit(ceId));
//        if (courseSubmitForm.getId() != null) {
//            courseSubmitVO.setSourceCode(courseSubmitForm.getSourceCode());
//        } else {
//            courseSubmitForm = new CourseSubmitForm();
//        }
//        modelAndView.setViewName(SUBMIT_VIEW);
//        modelAndView.addObject(SUBMIT_FORM, courseSubmitForm);
//        modelAndView.addObject(SUBMIT_VO, courseSubmitVO);
//        modelAndView.addObject(TAB, 3);
//        modelAndView.addObject(COURSE_ID, ceId);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkParticipate(authentication,#ceId)")
//    @PostMapping("/{ceId}/submit")
//    public ModelAndView doSubmit(@PathVariable("ceId") int ceId, CourseSubmitForm courseSubmitForm, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        courseSubmitForm.setScreenName("submitForm");
//        validate(courseSubmitForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            courseSubmitForm.setId(ceId);
//            return getSubmit(ceId, courseSubmitForm);
//        }
//        courseService.doSubmit(courseSubmitForm.getSourceCode(), ceId,
//                courseSubmitForm.getLeId(),
//                courseSubmitForm.getPmId());
//        modelAndView.setViewName("redirect:/course/" + ceId + "/my");
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkParticipate(authentication,#ceId)")
//    @GetMapping("/{ceId}/my")
//    public ModelAndView getMy(@PathVariable("ceId") int ceId) {
//        CourseLayoutForm courseSubmissionMyForm = new CourseSubmissionMyForm();
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(SUBMISSION_MY_VIEW);
//        modelAndView.addObject(Constants.TOPIC_TEXT, "course/" + ceId);
//        modelAndView.addObject(SUBMISSION_MY_FORM, courseSubmissionMyForm);
//        List<SubmissionDTO> lstSubmission = courseService.getSubmissionInCourse(ceId, false);
//        modelAndView.addObject(TAB, 4);
//        modelAndView.addObject(COURSE_ID, ceId);
//        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
//            "|| (@CourseValidator.checkParticipate(authentication,#ceId) && @CourseValidator.canViewStatus(#ceId))")
//    @GetMapping("/{ceId}/all")
//    public ModelAndView getAll(@PathVariable("ceId") int ceId) {
//        CourseLayoutForm courseSubmissionAllForm = new CourseSubmissionAllForm();
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(SUBMISSION_ALL_VIEW);
//        modelAndView.addObject(Constants.TOPIC_TEXT, "course/" + ceId);
//        modelAndView.addObject(SUBMISSION_ALL_FORM, courseSubmissionAllForm);
//        List<SubmissionDTO> lstSubmission = courseService.getSubmissionInCourse(ceId, true);
//        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
//        modelAndView.addObject(TAB, 5);
//        modelAndView.addObject(COURSE_ID, ceId);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
//            "|| (@CourseValidator.checkParticipate(authentication,#ceId) && @CourseValidator.canViewStatus(#ceId)) " +
//            "|| @CourseValidator.checkOutsiderPermission(authentication,#ceId)")
//    @GetMapping("/{ceId}/leaderboard")
//    public ModelAndView getLeaderboard(@PathVariable("ceId") int ceId) {
//        CourseLayoutForm courseLeaderboardForm = new CourseLeaderboardForm();
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        List<UserDTO> lstUser = courseService.getLeaderboardInfor(ceId);
//        List<ProblemDTO> lstProblem = courseService.getProblemForLeaderboard(ceId);
//        modelAndView.addObject("lstUser", lstUser);
//        modelAndView.addObject("lstProb", lstProblem);
//        modelAndView.setViewName(LEADERBOARD_VIEW);
//        modelAndView.addObject(LEADERBOARD_FORM, courseLeaderboardForm);
//        modelAndView.addObject(TAB, 6);
//        modelAndView.addObject(COURSE_ID, ceId);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
//            "|| @CourseValidator.checkParticipate(authentication,#ceId) " +
//            "|| @CourseValidator.checkOutsiderPermission(authentication,#ceId)")
//    @GetMapping("/{ceId}/announcement")
//    public ModelAndView getAnnouncement(@PathVariable("ceId") int ceId, CourseAnnouncementForm courseAnnouncementForm, boolean updateSuccess) {
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(ANNOUNCEMENT_VIEW);
//        List<ProblemDTO> lstProblem = courseService.getProblemToSubmit(ceId);
//        if (null == courseAnnouncementForm) {
//            courseAnnouncementForm = new CourseAnnouncementForm();
//        }
//        List<AnnouncementDTO> lstAnnounce = courseService.getAnnouncementListInContest(ceId);
//        modelAndView.addObject("courseAnnouncementForm", courseAnnouncementForm);
//        modelAndView.addObject("lstProblem", lstProblem);
//        modelAndView.addObject("lstAnnounce", lstAnnounce);
//        modelAndView.addObject(TAB, 7);
//        modelAndView.addObject(COURSE_ID, ceId);
//        modelAndView.addObject("updateSuccess", updateSuccess);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkParticipate(authentication,#ceId)")
//    @PostMapping("/{ceId}/addQuestion")
//    public ModelAndView addQuestion(@PathVariable("ceId") int ceId, CourseAnnouncementForm courseAnnouncementForm, BindingResult bindingResult) {
//        validate(courseAnnouncementForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return getAnnouncement(ceId, courseAnnouncementForm, false);
//        }
//        courseService.addQuestion(ceId, Integer.parseInt(courseAnnouncementForm.getPmId()), courseAnnouncementForm.getQuestion());
//        return getAnnouncement(ceId, courseAnnouncementForm, true);
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @PostMapping("/{ceId}/addAnnouncement")
//    public ModelAndView addAnnouncement(@PathVariable("ceId") int ceId, CourseAnnouncementForm courseAnnouncementForm, BindingResult bindingResult) {
//        validate(courseAnnouncementForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return getAnnouncement(ceId, courseAnnouncementForm, false);
//        }
//        courseService.addAnnouncement(ceId, Integer.parseInt(courseAnnouncementForm.getPmId()), courseAnnouncementForm.getAnswer());
//        return getAnnouncement(ceId, courseAnnouncementForm, true);
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
//            "|| @CourseValidator.checkShowSubmitPolicy(#ceId,#snId)")
//    @GetMapping("/{ceId}/submission/{snId}")
//    public ModelAndView getSubmission(@PathVariable("ceId") int ceId, @PathVariable("snId") int snId) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName(SUBMISSION_VIEW);
//        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId, ceId);
//        modelAndView.addObject("submitDetail", submissionDTO);
//        modelAndView.addObject(TAB, 0);
//        modelAndView.addObject(COURSE_ID, ceId);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "') ")
//    @GetMapping("/{ceId}/setting")
//    public ModelAndView getSetting(@PathVariable("ceId") int ceId, CourseSettingForm courseSettingForm, boolean updateSuccess) {
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(SETTING_VIEW);
//        CourseSettingVO courseSettingVO = new CourseSettingVO();
//        if (null == courseSettingForm.getId()) {
//            courseSettingForm = new CourseSettingForm();
//            CourseDTO courseDTO = courseService.getCourseInfo(ceId);
//            modelMapper.map(courseDTO, courseSettingVO);
//        } else {
//            modelMapper.map(courseSettingForm, courseSettingVO);
//        }
//        modelAndView.addObject("updateSuccess", updateSuccess);
//        modelAndView.addObject(SETTING_FORM, courseSettingForm);
//        modelAndView.addObject(TAB, 8);
//        modelAndView.addObject(COURSE_ID, ceId);
//        modelAndView.addObject("courseSettingVO", courseSettingVO);
//        modelAndView.addObject("courseSettingForm", courseSettingForm);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "') ")
//    @PostMapping("/{ceId}/setting")
//    public ModelAndView updateSetting(@PathVariable("ceId") int ceId, CourseSettingForm courseSettingForm, BindingResult bindingResult) {
//        validate(courseSettingForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            courseSettingForm.setId(ceId);
//            return getSetting(ceId, courseSettingForm, false);
//        }
//        try {
//            courseSettingForm.setId(ceId);
//            courseService.updateCourse(courseSettingForm);
//        } catch (Exception e) {
//            e.printStackTrace();
//            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
//        }
//        return getSetting(ceId, courseSettingForm, true);
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ceId)")
//    @GetMapping("/{ceId}/role")
//    public ModelAndView getRole(@PathVariable("ceId") int ceId, boolean updateSuccess) {
//        CourseLayoutForm courseRoleForm = new CourseRoleForm();
//        ModelAndView modelAndView = createGeneralModel(ceId);
//        modelAndView.setViewName(ROLE_VIEW);
//        modelAndView.addObject(ROLE_FORM, courseRoleForm);
//        modelAndView.addObject(TAB, 9);
//        modelAndView.addObject(COURSE_ID, ceId);
//        List<UserDTO> lstUser = courseService.getListCourseRole(ceId);
//        Integer currentUrId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
//        String currentUrName = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_FULLNAME);
//        String currentUrHandle = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
//        Integer currentReId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_ID);
//        String currentReName = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_NAME);
//        modelAndView.addObject("lstUser", lstUser);
//        modelAndView.addObject("currentUrId", currentUrId);
//        modelAndView.addObject("currentUrName", currentUrName);
//        modelAndView.addObject("currentReId", currentReId);
//        modelAndView.addObject("currentUrHandle", currentUrHandle);
//        modelAndView.addObject("currentReName", currentReName);
//        if (updateSuccess) {
//            modelAndView.addObject(UPDATE_SUCCESS, true);
//        }
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ceId)")
//    @GetMapping("/{ceId}/addRole")
//    public ModelAndView getAddRole(@PathVariable("ceId") Integer ceId, CourseAddRoleForm courseAddRoleForm,
//                                   boolean updateSuccess) {
//        ModelAndView modelAndView = new ModelAndView();
//        if (null == courseAddRoleForm || null == courseAddRoleForm.getId()) {
//            courseAddRoleForm = new CourseAddRoleForm();
//        }
//        modelAndView.setViewName(ADD_ROLE_VIEW);
//        modelAndView.addObject("ceId", ceId);
//        modelAndView.addObject("courseAddRoleForm", courseAddRoleForm);
//        if (updateSuccess) {
//            modelAndView.addObject(UPDATE_SUCCESS, true);
//        }
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ceId)")
//    @PostMapping("/{ceId}/findForRole")
//    public ModelAndView findForAddRole(@PathVariable("ceId") Integer ceId, CourseAddRoleForm courseAddRoleForm) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName(ADD_ROLE_VIEW);
//        List<UserDTO> lstUser = courseService.findUserForCourseRole(courseAddRoleForm.getFullname(), Integer.parseInt(courseAddRoleForm.getReId()), ceId);
//        modelAndView.addObject("lstUser", lstUser);
//        return modelAndView;
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ceId)")
//    @PostMapping("/{ceId}/addRole")
//    public ModelAndView addRole(@PathVariable("ceId") Integer ceId, CourseAddRoleForm courseAddRoleForm) throws UserTryingToBeSmartException {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName(ADD_ROLE_VIEW);
//        courseService.addRole(courseAddRoleForm.getLstUrId(), Integer.parseInt(courseAddRoleForm.getAuyId()), ceId);
//        return getAddRole(ceId, courseAddRoleForm, true);
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ceId)")
//    @GetMapping("/{ceId}/deleteRole/{urId}")
//    public ModelAndView deleteRole(@PathVariable("ceId") Integer ceId, @PathVariable("urId") Integer urId) throws UserTryingToBeSmartException {
//        Integer currentUrId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
//        if (currentUrId.equals(urId)) {
//            throw new UserTryingToBeSmartException();
//        }
//        courseService.deleteRole(ceId, urId);
//        return getRole(ceId, true);
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @GetMapping("/{ceId}/changeAnnounceState/{atId}/to/{newState}")
//    public ModelAndView changeAnnounceState(@PathVariable("ceId") Integer ceId, @PathVariable("atId") Integer atId, @PathVariable("newState") Integer newState) {
//        courseService.changeAnnounceHiddenState(atId, newState);
//        return getAnnouncement(ceId, null, true);
//    }
//
//    @CheckNotNullFirst
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @GetMapping("/{ceId}/answer/{atId}")
//    public ModelAndView getQuestion(@PathVariable("ceId") Integer ceId, @PathVariable("atId") Integer atId, CourseAnswerForm courseAnswerForm, boolean updateSuccess) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName(ANSWER_VIEW);
//        String question = courseService.getQuestion(atId);
//        modelAndView.addObject("question", question);
//        modelAndView.addObject("ceId", ceId);
//        modelAndView.addObject("atId", atId);
//        if (null == courseAnswerForm) {
//            courseAnswerForm = new CourseAnswerForm();
//        }
//        modelAndView.addObject("courseAnswerForm", courseAnswerForm);
//        modelAndView.addObject(UPDATE_SUCCESS, updateSuccess);
//        return modelAndView;
//    }
//
//    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ceId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
//    @PostMapping("/{ceId}/answer/{atId}")
//    public ModelAndView doAnswer(@PathVariable("ceId") Integer ceId, @PathVariable("atId") Integer atId, CourseAnswerForm courseAnswerForm, BindingResult bindingResult) {
//        validate(courseAnswerForm, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return getQuestion(ceId, atId, courseAnswerForm, false);
//        }
//        courseService.answerQuestion(Integer.parseInt(courseAnswerForm.getAtId()), courseAnswerForm.getAnswer());
//        return getQuestion(ceId, atId, courseAnswerForm, true);
//    }
}


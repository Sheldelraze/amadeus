//package com.minh.nguyen.controller;
//
//import com.minh.nguyen.constants.Constants;
//import com.minh.nguyen.controller.common.BaseController;
//import com.minh.nguyen.dto.UserDTO;
//import com.minh.nguyen.exception.UserTryingToBeSmartException;
//import com.minh.nguyen.service.CourseService;
//import com.minh.nguyen.service.GeneralService;
//import com.minh.nguyen.service.ProblemService;
//import com.minh.nguyen.util.StringUtil;
//import com.minh.nguyen.validator.CourseValidator;
//import com.minh.nguyen.validator.annotation.CheckNotNullFirst;
//import com.minh.nguyen.validator.annotation.CheckNotNullThird;
//import com.minh.nguyen.vo.problem.ProblemPreviewVO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
///**
// * @author Mr.Minh
// * @since 14/03/2018
// * Purpose:
// */
//@Controller("CourseController")
//@RequestMapping("/course")
//public class CourseController extends BaseController {
//
//    @Autowired
//    private CourseService courseService;
//
//    @Autowired
//    private ProblemService problemService;
//
//    @Autowired
//    private GeneralService generalService;
//
//    @Autowired
//    private HttpSession httpSession;
//
//    @Autowired
//    private CourseValidator courseValidator;
//
//    private ModelAndView createGeneralModel(int ctId) {
//
//        //add common information
//        ModelAndView modelAndView = createGeneralModel();
//
//        //add authority of current user (CAN_VIEW and CAN_PARTICIPATE only)
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (null != auth && !StringUtil.isNull(auth.getName())) {
//            if (courseValidator.checkPermission(auth, ctId, Constants.AUTH_EDIT_COURSE_TEXT)) {
//                modelAndView.addObject(AUTHORITY, Constants.AUTH_EDIT_COURSE_TEXT);
//            } else if (courseValidator.checkPermission(auth, ctId, Constants.AUTH_PARTICIPATE_COURSE_TEXT)) {
//                modelAndView.addObject(AUTHORITY, Constants.AUTH_PARTICIPATE_COURSE_TEXT);
//            } else {
//                modelAndView.addObject(AUTHORITY, "No special authority");
//            }
//        }
//
//        //check if user can view common status
//        boolean canViewStatus = false;
//        if (null != auth && !StringUtil.isNull(auth.getName())) {
//            List<Integer> defaultAuth = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
//            if (null != defaultAuth && defaultAuth.contains(Constants.AUTH_VIEW_ALL_COURSE_ID)) {
//                canViewStatus = true;
//            } else if (courseValidator.checkPermission(auth, ctId, Constants.AUTH_VIEW_COURSE_TEXT)) {
//                canViewStatus = true;
//            } else {
//                if (courseValidator.checkParticipate(auth, ctId) && courseValidator.canViewStatus(ctId)) {
//                    canViewStatus = true;
//                } else if (courseValidator.checkOutsiderPermission(auth, ctId)) {
//                    canViewStatus = true;
//                }
//            }
//        }
//        modelAndView.addObject("canViewStatus", canViewStatus);
//
//        //add course's creator check
//        boolean isCreator = false;
//        if (null != auth && !StringUtil.isNull(auth.getName())) {
//            if (courseValidator.checkCreator(auth, ctId)) {
//                isCreator = true;
//            }
//        }
//        modelAndView.addObject("isCreator", isCreator);
//
//        //add announcement count if any
//        Integer atCnt = courseService.getAnnouncementCount(ctId);
//        if (atCnt > 0) {
//            modelAndView.addObject("atCnt", atCnt);
//        }
//        return modelAndView;
//    }
//
////    @GetMapping("/all")
////    public ModelAndView getAllCourse() {
////        ModelAndView modelAndView = createGeneralModel();
////        ;
////        modelAndView.setViewName(LIST_ALL_VIEW);
////        CourseListVO courseListVO = new CourseListVO();
////        courseService.setListCourse(courseListVO);
////        modelAndView.addObject("courseListVO", courseListVO);
////        return modelAndView;
////    }
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_COURSE_TEXT + "')")
////    @GetMapping("/create")
////    public ModelAndView createCourse(CourseCreateForm courseCreateForm) {
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName(CREATE_VIEW);
////        if (null == courseCreateForm) {
////            courseCreateForm = new CourseCreateForm();
////        }
////        modelAndView.addObject(CREATE_FORM, courseCreateForm);
////        return modelAndView;
////    }
////
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_CREATE_COURSE_TEXT + "')")
////    @PostMapping("/doCreate")
////    public ModelAndView doCreate(CourseCreateForm courseCreateForm, BindingResult bindingResult) {
////        validate(courseCreateForm, bindingResult);
////        if (bindingResult.hasErrors()) {
////            return createCourse(courseCreateForm);
////        }
////        try {
////            CourseDTO courseDTO = new CourseDTO();
////            modelMapper.map(courseCreateForm, courseDTO);
////            int ctId = courseService.createCourse(courseDTO);
////            return getInformation(ctId);
////        } catch (Exception e) {
////            e.printStackTrace();
////            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
////            return createCourse(courseCreateForm);
////        }
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("@CourseValidator.checkPublic(#ctId) " +
////            "|| (hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') " +
////            "|| @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "'))")
////    @GetMapping({"/{ctId}/information", "/{ctId}/", "/{ctId}"})
////    public ModelAndView getInformation(@PathVariable("ctId") int ctId) {
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(INFORMATION_VIEW);
////        CourseInformationVO courseInformationVO = courseService.getInformation(ctId);
////        modelAndView.addObject("courseInformationVO", courseInformationVO);
////        modelAndView.addObject(TAB, 1);
////        modelAndView.addObject(COURSE_ID, ctId);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @GetMapping("/{ctId}/addProblem")
////    public ModelAndView initAddProblem(@PathVariable("ctId") int ctId, CourseAddProblemForm courseAddProblemForm,
////                                       Boolean updateSuccess) {
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(ADD_PROBLEM_VIEW);
////        if (null == courseAddProblemForm) {
////            courseAddProblemForm = new CourseAddProblemForm();
////        }
////        List<ProblemDTO> lstProblemDTO = courseService.getProblemToAdd(ctId);
////        courseAddProblemForm.setLstProblemDTO(lstProblemDTO);
////        modelAndView.addObject("updateSuccess", updateSuccess);
////        modelAndView.addObject("ctId", ctId);
////        modelAndView.addObject("courseAddProblemForm", courseAddProblemForm);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @PostMapping("/{ctId}/addProblem")
////    public ModelAndView doAddProblem(@PathVariable("ctId") int ctId,
////                                     CourseAddProblemForm courseAddProblemForm,
////                                     BindingResult bindingResult) {
////        try {
////            courseService.addProblemToCourse(ctId, courseAddProblemForm.getLstPmId());
////        } catch (Exception e) {
////            e.printStackTrace();
////            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR);
////        }
////        if (bindingResult.hasErrors()) {
////            return initAddProblem(ctId, courseAddProblemForm, false);
////        }
////        return initAddProblem(ctId, courseAddProblemForm, true);
////    }
////
////    @CheckNotNullFirst
////    @CheckNotNullThird
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @GetMapping("/{ctId}/showProblem/{pmId}")
////    public ModelAndView showProblem(@PathVariable("ctId") int ctId, @PathVariable("pmId") int pmId) {
////        courseService.setProblemHiddenStatus(ctId, pmId, Constants.STATUS_SHOW);
////        return getProblem(ctId);
////    }
////
////    @CheckNotNullFirst
////    @CheckNotNullThird
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @GetMapping("/{ctId}/hideProblem/{pmId}")
////    public ModelAndView hideProblem(@PathVariable("ctId") int ctId, @PathVariable("pmId") int pmId) {
////        courseService.setProblemHiddenStatus(ctId, pmId, Constants.STATUS_HIDDEN);
////        return getProblem(ctId);
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
////            "|| @CourseValidator.checkParticipate(authentication,#ctId) " +
////            "|| @CourseValidator.checkOutsiderPermission(authentication,#ctId)")
////    @GetMapping("/{ctId}/problem")
////    public ModelAndView getProblem(@PathVariable("ctId") int ctId) {
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(PROBLEM_LIST_VIEW);
////        List<ProblemDTO> lstProblemDTO = courseService.getProblemToDisplay(ctId);
////        CourseProblemVO courseProblemVO = new CourseProblemVO();
////        courseProblemVO.setLstProblemDTO(lstProblemDTO);
////        modelAndView.addObject(TAB, 2);
////        modelAndView.addObject("courseProblemVO", courseProblemVO);
////        modelAndView.addObject(COURSE_ID, ctId);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
////            "|| @CourseValidator.checkParticipate(authentication,#ctId) " +
////            "|| @CourseValidator.checkOutsiderPermission(authentication,#ctId)")
////    @GetMapping("/{ctId}/problem/{pmId}/view")
////    public ModelAndView viewProblem(@PathVariable("ctId") int ctId, @PathVariable("pmId") int pmId) {
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(PROBLEM_VIEW);
////        ProblemDTO problemDTO = new ProblemDTO();
////        problemDTO.setId(pmId);
////        problemService.getProblemInfo(problemDTO);
////        problemService.getShowInStatementTest(problemDTO);
////        ProblemPreviewVO problemPreviewVO = new ProblemPreviewVO();
////        if (null != problemPreviewVO.getNote() && StringUtil.checkBlank(problemPreviewVO.getNote())) {
////            problemPreviewVO.setNote(null);
////        }
////        modelMapper.map(problemDTO, problemPreviewVO);
////        modelAndView.addObject(TAB, 2);
////        modelAndView.addObject("problemVO", problemPreviewVO);
////        modelAndView.addObject(COURSE_ID, ctId);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
////            "|| @CourseValidator.checkParticipate(authentication,#ctId)")
////    @GetMapping("/{ctId}/submit")
////    public ModelAndView getSubmit(@PathVariable("ctId") int ctId, CourseSubmitForm courseSubmitForm) {
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        CourseSubmitVO courseSubmitVO = new CourseSubmitVO();
////        courseSubmitVO.setLstLanguage(problemService.getAllLanguage());
////        courseSubmitVO.setLstProblem(courseService.getProblemToSubmit(ctId));
////        if (courseSubmitForm.getId() != null) {
////            courseSubmitVO.setSourceCode(courseSubmitForm.getSourceCode());
////        } else {
////            courseSubmitForm = new CourseSubmitForm();
////        }
////        modelAndView.setViewName(SUBMIT_VIEW);
////        modelAndView.addObject(SUBMIT_FORM, courseSubmitForm);
////        modelAndView.addObject(SUBMIT_VO, courseSubmitVO);
////        modelAndView.addObject(TAB, 3);
////        modelAndView.addObject(COURSE_ID, ctId);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkParticipate(authentication,#ctId)")
////    @PostMapping("/{ctId}/submit")
////    public ModelAndView doSubmit(@PathVariable("ctId") int ctId, CourseSubmitForm courseSubmitForm, BindingResult bindingResult) {
////        ModelAndView modelAndView = new ModelAndView();
////        courseSubmitForm.setScreenName("submitForm");
////        validate(courseSubmitForm, bindingResult);
////        if (bindingResult.hasErrors()) {
////            courseSubmitForm.setId(ctId);
////            return getSubmit(ctId, courseSubmitForm);
////        }
////        courseService.doSubmit(courseSubmitForm.getSourceCode(), ctId,
////                courseSubmitForm.getLeId(),
////                courseSubmitForm.getPmId());
////        modelAndView.setViewName("redirect:/course/" + ctId + "/my");
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkParticipate(authentication,#ctId)")
////    @GetMapping("/{ctId}/my")
////    public ModelAndView getMy(@PathVariable("ctId") int ctId) {
////        CourseLayoutForm courseSubmissionMyForm = new CourseSubmissionMyForm();
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(SUBMISSION_MY_VIEW);
////        modelAndView.addObject(Constants.TOPIC_TEXT, "course/" + ctId);
////        modelAndView.addObject(SUBMISSION_MY_FORM, courseSubmissionMyForm);
////        List<SubmissionDTO> lstSubmission = courseService.getSubmissionInCourse(ctId, false);
////        modelAndView.addObject(TAB, 4);
////        modelAndView.addObject(COURSE_ID, ctId);
////        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
////            "|| (@CourseValidator.checkParticipate(authentication,#ctId) && @CourseValidator.canViewStatus(#ctId))")
////    @GetMapping("/{ctId}/all")
////    public ModelAndView getAll(@PathVariable("ctId") int ctId) {
////        CourseLayoutForm courseSubmissionAllForm = new CourseSubmissionAllForm();
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(SUBMISSION_ALL_VIEW);
////        modelAndView.addObject(Constants.TOPIC_TEXT, "course/" + ctId);
////        modelAndView.addObject(SUBMISSION_ALL_FORM, courseSubmissionAllForm);
////        List<SubmissionDTO> lstSubmission = courseService.getSubmissionInCourse(ctId, true);
////        modelAndView.addObject(SUBMISSION_LIST, lstSubmission);
////        modelAndView.addObject(TAB, 5);
////        modelAndView.addObject(COURSE_ID, ctId);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
////            "|| (@CourseValidator.checkParticipate(authentication,#ctId) && @CourseValidator.canViewStatus(#ctId)) " +
////            "|| @CourseValidator.checkOutsiderPermission(authentication,#ctId)")
////    @GetMapping("/{ctId}/leaderboard")
////    public ModelAndView getLeaderboard(@PathVariable("ctId") int ctId) {
////        CourseLayoutForm courseLeaderboardForm = new CourseLeaderboardForm();
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        List<UserDTO> lstUser = courseService.getLeaderboardInfor(ctId);
////        List<ProblemDTO> lstProblem = courseService.getProblemForLeaderboard(ctId);
////        modelAndView.addObject("lstUser", lstUser);
////        modelAndView.addObject("lstProb", lstProblem);
////        modelAndView.setViewName(LEADERBOARD_VIEW);
////        modelAndView.addObject(LEADERBOARD_FORM, courseLeaderboardForm);
////        modelAndView.addObject(TAB, 6);
////        modelAndView.addObject(COURSE_ID, ctId);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
////            "|| @CourseValidator.checkParticipate(authentication,#ctId) " +
////            "|| @CourseValidator.checkOutsiderPermission(authentication,#ctId)")
////    @GetMapping("/{ctId}/announcement")
////    public ModelAndView getAnnouncement(@PathVariable("ctId") int ctId, CourseAnnouncementForm courseAnnouncementForm, boolean updateSuccess) {
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(ANNOUNCEMENT_VIEW);
////        List<ProblemDTO> lstProblem = courseService.getProblemToSubmit(ctId);
////        if (null == courseAnnouncementForm) {
////            courseAnnouncementForm = new CourseAnnouncementForm();
////        }
////        List<AnnouncementDTO> lstAnnounce = courseService.getAnnouncementList(ctId);
////        modelAndView.addObject("courseAnnouncementForm", courseAnnouncementForm);
////        modelAndView.addObject("lstProblem", lstProblem);
////        modelAndView.addObject("lstAnnounce", lstAnnounce);
////        modelAndView.addObject(TAB, 7);
////        modelAndView.addObject(COURSE_ID, ctId);
////        modelAndView.addObject("updateSuccess", updateSuccess);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkParticipate(authentication,#ctId)")
////    @PostMapping("/{ctId}/addQuestion")
////    public ModelAndView addQuestion(@PathVariable("ctId") int ctId, CourseAnnouncementForm courseAnnouncementForm, BindingResult bindingResult) {
////        validate(courseAnnouncementForm, bindingResult);
////        if (bindingResult.hasErrors()) {
////            return getAnnouncement(ctId, courseAnnouncementForm, false);
////        }
////        courseService.addQuestion(ctId, Integer.parseInt(courseAnnouncementForm.getPmId()), courseAnnouncementForm.getQuestion());
////        return getAnnouncement(ctId, courseAnnouncementForm, true);
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @PostMapping("/{ctId}/addAnnouncement")
////    public ModelAndView addAnnouncement(@PathVariable("ctId") int ctId, CourseAnnouncementForm courseAnnouncementForm, BindingResult bindingResult) {
////        validate(courseAnnouncementForm, bindingResult);
////        if (bindingResult.hasErrors()) {
////            return getAnnouncement(ctId, courseAnnouncementForm, false);
////        }
////        courseService.addAnnouncement(ctId, Integer.parseInt(courseAnnouncementForm.getPmId()), courseAnnouncementForm.getAnswer());
////        return getAnnouncement(ctId, courseAnnouncementForm, true);
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("hasAuthority('" + Constants.AUTH_VIEW_ALL_COURSE_TEXT + "') || @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_VIEW_COURSE_TEXT + "') " +
////            "|| @CourseValidator.checkShowSubmitPolicy(#ctId,#snId)")
////    @GetMapping("/{ctId}/submission/{snId}")
////    public ModelAndView getSubmission(@PathVariable("ctId") int ctId, @PathVariable("snId") int snId) {
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName(SUBMISSION_VIEW);
////        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId, ctId);
////        modelAndView.addObject("submitDetail", submissionDTO);
////        modelAndView.addObject(TAB, 0);
////        modelAndView.addObject(COURSE_ID, ctId);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "') ")
////    @GetMapping("/{ctId}/setting")
////    public ModelAndView getSetting(@PathVariable("ctId") int ctId, CourseSettingForm courseSettingForm, boolean updateSuccess) {
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(SETTING_VIEW);
////        CourseSettingVO courseSettingVO = new CourseSettingVO();
////        if (null == courseSettingForm.getId()) {
////            courseSettingForm = new CourseSettingForm();
////            CourseDTO courseDTO = courseService.getCourseInfo(ctId);
////            modelMapper.map(courseDTO, courseSettingVO);
////        } else {
////            modelMapper.map(courseSettingForm, courseSettingVO);
////        }
////        modelAndView.addObject("updateSuccess", updateSuccess);
////        modelAndView.addObject(SETTING_FORM, courseSettingForm);
////        modelAndView.addObject(TAB, 8);
////        modelAndView.addObject(COURSE_ID, ctId);
////        modelAndView.addObject("courseSettingVO", courseSettingVO);
////        modelAndView.addObject("courseSettingForm", courseSettingForm);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "') ")
////    @PostMapping("/{ctId}/setting")
////    public ModelAndView updateSetting(@PathVariable("ctId") int ctId, CourseSettingForm courseSettingForm, BindingResult bindingResult) {
////        validate(courseSettingForm, bindingResult);
////        if (bindingResult.hasErrors()) {
////            courseSettingForm.setId(ctId);
////            return getSetting(ctId, courseSettingForm, false);
////        }
////        try {
////            courseSettingForm.setId(ctId);
////            courseService.updateCourse(courseSettingForm);
////        } catch (Exception e) {
////            e.printStackTrace();
////            addLogicError(bindingResult, Constants.MSG_SYSTEM_ERR, new Object[]{});
////        }
////        return getSetting(ctId, courseSettingForm, true);
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ctId)")
////    @GetMapping("/{ctId}/role")
////    public ModelAndView getRole(@PathVariable("ctId") int ctId, boolean updateSuccess) {
////        CourseLayoutForm courseRoleForm = new CourseRoleForm();
////        ModelAndView modelAndView = createGeneralModel(ctId);
////        modelAndView.setViewName(ROLE_VIEW);
////        modelAndView.addObject(ROLE_FORM, courseRoleForm);
////        modelAndView.addObject(TAB, 9);
////        modelAndView.addObject(COURSE_ID, ctId);
////        List<UserDTO> lstUser = courseService.getListCourseRole(ctId);
////        Integer currentUrId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
////        String currentUrName = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_FULLNAME);
////        String currentUrHandle = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
////        Integer currentReId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_ID);
////        String currentReName = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_NAME);
////        modelAndView.addObject("lstUser", lstUser);
////        modelAndView.addObject("currentUrId", currentUrId);
////        modelAndView.addObject("currentUrName", currentUrName);
////        modelAndView.addObject("currentReId", currentReId);
////        modelAndView.addObject("currentUrHandle", currentUrHandle);
////        modelAndView.addObject("currentReName", currentReName);
////        if (updateSuccess) {
////            modelAndView.addObject(UPDATE_SUCCESS, true);
////        }
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ctId)")
////    @GetMapping("/{ctId}/addRole")
////    public ModelAndView getAddRole(@PathVariable("ctId") Integer ctId, CourseAddRoleForm courseAddRoleForm,
////                                   boolean updateSuccess) {
////        ModelAndView modelAndView = new ModelAndView();
////        if (null == courseAddRoleForm || null == courseAddRoleForm.getId()) {
////            courseAddRoleForm = new CourseAddRoleForm();
////        }
////        modelAndView.setViewName(ADD_ROLE_VIEW);
////        modelAndView.addObject("ctId", ctId);
////        modelAndView.addObject("courseAddRoleForm", courseAddRoleForm);
////        if (updateSuccess) {
////            modelAndView.addObject(UPDATE_SUCCESS, true);
////        }
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ctId)")
////    @PostMapping("/{ctId}/findForRole")
////    public ModelAndView findForAddRole(@PathVariable("ctId") Integer ctId, CourseAddRoleForm courseAddRoleForm) {
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName(ADD_ROLE_VIEW);
////        List<UserDTO> lstUser = courseService.findUserForCourseRole(courseAddRoleForm.getFullname(), Integer.parseInt(courseAddRoleForm.getReId()), ctId);
////        modelAndView.addObject("lstUser", lstUser);
////        return modelAndView;
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ctId)")
////    @PostMapping("/{ctId}/addRole")
////    public ModelAndView addRole(@PathVariable("ctId") Integer ctId, CourseAddRoleForm courseAddRoleForm) throws UserTryingToBeSmartException {
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName(ADD_ROLE_VIEW);
////        courseService.addRole(courseAddRoleForm.getLstUrId(), Integer.parseInt(courseAddRoleForm.getAuyId()), ctId);
////        return getAddRole(ctId, courseAddRoleForm, true);
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkCreator(authentication,#ctId)")
////    @GetMapping("/{ctId}/deleteRole/{urId}")
////    public ModelAndView deleteRole(@PathVariable("ctId") Integer ctId, @PathVariable("urId") Integer urId) throws UserTryingToBeSmartException {
////        Integer currentUrId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
////        if (currentUrId.equals(urId)) {
////            throw new UserTryingToBeSmartException();
////        }
////        courseService.deleteRole(ctId, urId);
////        return getRole(ctId, true);
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @GetMapping("/{ctId}/changeAnnounceState/{atId}/to/{newState}")
////    public ModelAndView changeAnnounceState(@PathVariable("ctId") Integer ctId, @PathVariable("atId") Integer atId, @PathVariable("newState") Integer newState) {
////        courseService.changeAnnounceHiddenState(atId, newState);
////        return getAnnouncement(ctId, null, true);
////    }
////
////    @CheckNotNullFirst
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @GetMapping("/{ctId}/answer/{atId}")
////    public ModelAndView getQuestion(@PathVariable("ctId") Integer ctId, @PathVariable("atId") Integer atId, CourseAnswerForm courseAnswerForm, boolean updateSuccess) {
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName(ANSWER_VIEW);
////        String question = courseService.getQuestion(atId);
////        modelAndView.addObject("question", question);
////        modelAndView.addObject("ctId", ctId);
////        modelAndView.addObject("atId", atId);
////        if (null == courseAnswerForm) {
////            courseAnswerForm = new CourseAnswerForm();
////        }
////        modelAndView.addObject("courseAnswerForm", courseAnswerForm);
////        modelAndView.addObject(UPDATE_SUCCESS, updateSuccess);
////        return modelAndView;
////    }
////
////    @PreAuthorize("isAuthenticated() && @CourseValidator.checkPermission(authentication,#ctId,'" + Constants.AUTH_EDIT_COURSE_TEXT + "')")
////    @PostMapping("/{ctId}/answer/{atId}")
////    public ModelAndView doAnswer(@PathVariable("ctId") Integer ctId, @PathVariable("atId") Integer atId, CourseAnswerForm courseAnswerForm, BindingResult bindingResult) {
////        validate(courseAnswerForm, bindingResult);
////        if (bindingResult.hasErrors()) {
////            return getQuestion(ctId, atId, courseAnswerForm, false);
////        }
////        courseService.answerQuestion(Integer.parseInt(courseAnswerForm.getAtId()), courseAnswerForm.getAnswer());
////        return getQuestion(ctId, atId, courseAnswerForm, true);
////    }
//}
//

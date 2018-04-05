package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.*;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
import com.minh.nguyen.form.course.CourseSettingForm;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.CourseValidator;
import com.minh.nguyen.vo.course.CourseListVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.*;
import java.util.*;

/**
 * @author Mr.Minh
 * @since 14/03/2018
 * Purpose:
 */
@Service
public class CourseService extends BaseService {

    @Autowired
    private CeMlMapper ceMlMapper;

    @Autowired
    private CePmMapper cePmMapper;

    @Autowired
    private CeSnMapper ceSnMapper;

    @Autowired
    private CeAtMapper ceAtMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private CourseValidator courseValidator;

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UrCeAuyMapper urCeAuyMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private LanguageMapper languageMapper;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private InputMapper inputMapper;

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private GeneralService generalService;

    @Autowired
    private SimpMessageSendingOperations simpMessagingTemplate;

    public Integer getAnnouncementCount(Integer ctId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean getAllAnnouncement = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (courseValidator.checkPermission(auth, ctId, Constants.AUTH_EDIT_COURSE_TEXT)) {
                getAllAnnouncement = true;
            }
        }
        return announcementMapper.countAnnouncementListInCourse(ctId, getAllAnnouncement);
    }

    public void updateCourse(CourseSettingForm contestSettingForm) throws Exception {
        CourseEntity courseEntity = new CourseEntity();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        modelMapper.map(contestSettingForm, courseEntity);
        courseEntity.setStartTime(dateFormat.parse(contestSettingForm.getStartTime()));
        courseEntity.setEndTime(dateFormat.parse(contestSettingForm.getEndTime()));
        setUpdateInfo(courseEntity);
        courseMapper.updateNotNullByPK(courseEntity);
    }

    @Transactional
    public int createCourse(CourseDTO courseDTO) throws ParseException {
        CourseEntity courseEntity = new CourseEntity();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        courseEntity.setStartTime(formatter.parse(courseDTO.getStartTime()));
        courseEntity.setEndTime(formatter.parse(courseDTO.getEndTime()));
        courseEntity.setName(courseDTO.getName());
        courseEntity.setShowSubmit(Constants.SHOW_SUBMIT_ALL);
        try {
            //set initial course info and insert
            setCreateInfo(courseEntity);
            setUpdateInfo(courseEntity);
            int insertRecord = courseMapper.insertCourse(courseEntity);
            if (insertRecord == 0) {
                rollBack(Constants.MSG_INSERT_ERR);
            }

            //get current loggin user
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserEntity userEntity = new UserEntity();
            userEntity.setHandle(auth.getName());
            List<UserEntity> lstUser = userMapper.selectWithExample(userEntity);

            //assume that only 1 user has current handle
            if (lstUser.size() != 1) {
                rollBack(Constants.MSG_SYSTEM_ERR);
            }
            userEntity = lstUser.get(0);

            //insert view course authority
            UrCeAuyEntity urCeAuyEntity = new UrCeAuyEntity();
            urCeAuyEntity.setUrId(userEntity.getId());
            urCeAuyEntity.setCeId(courseEntity.getId());
            urCeAuyEntity.setAuyId(Constants.AUTH_VIEW_COURSE_ID);
            setCreateInfo(urCeAuyEntity);
            setUpdateInfo(urCeAuyEntity);
            insertRecord = urCeAuyMapper.insert(urCeAuyEntity);

            //assume that insert success
            if (insertRecord != 1) {
                rollBack(Constants.MSG_INSERT_ERR);
            }

            //insert edit course authority
            urCeAuyEntity.setAuyId(Constants.AUTH_EDIT_COURSE_ID);
            insertRecord = urCeAuyMapper.insert(urCeAuyEntity);
            if (insertRecord != 1) {
                rollBack(Constants.MSG_SYSTEM_ERR);
            }

            return courseEntity.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void setMaterialHiddenStatus(Integer ceId, Integer mlId, Integer status) {
        CeMlEntity ceMlEntity = new CeMlEntity();
        setUpdateInfo(ceMlEntity);
        ceMlEntity.setCeId(ceId);
        ceMlEntity.setMlId(mlId);
        ceMlEntity.setIsHidden(status);
        ceMlMapper.updateNotNullByPK(ceMlEntity);
    }

    public List<UserDTO> getListCourseRole(Integer currentUserId, int ceId) {
        List<UserDTO> lstUser = userMapper.getListCourseRole(currentUserId, ceId);

        return lstUser;
    }

    public List<UserDTO> findUserForCourseRole(String fullname, Integer reId, Integer ceId) {
        List<UserDTO> lstUser = userMapper.findUserForCourseRole(fullname, reId, ceId);
        return lstUser;
    }

    /**
     * addRole
     * 1 = CAN_VIEW_COURSE
     * 2 = CAN_VIEW_COURSE + CAN_EDIT_COURSER
     * 3 = CAN_PARTICIPATE_COURSE
     */
    public void addRole(String[] urId, Integer auyId, Integer ceId) throws UserTryingToBeSmartException {
        UrCeAuyEntity urCeAuyEntity = new UrCeAuyEntity();
        setCreateInfo(urCeAuyEntity);
        setUpdateInfo(urCeAuyEntity);
        urCeAuyEntity.setCeId(ceId);
        if (auyId != 1 && auyId != 2 && auyId != 3) {
            throw new UserTryingToBeSmartException();
        }
        //if auyId == 1 or 2
        if (auyId == 1 || auyId == 2) {
            urCeAuyEntity.setAuyId(Constants.AUTH_VIEW_COURSE_ID);
            for (String id : urId) {
                urCeAuyEntity.setUrId(Integer.parseInt(id));
                urCeAuyMapper.insert(urCeAuyEntity);
            }
        }
        if (auyId == 2) {
            urCeAuyEntity.setAuyId(Constants.AUTH_EDIT_COURSE_ID);
            for (String id : urId) {
                urCeAuyEntity.setUrId(Integer.parseInt(id));
                urCeAuyMapper.insert(urCeAuyEntity);
            }
        }

        if (auyId == 3) {
            urCeAuyEntity.setAuyId(Constants.AUTH_PARTICIPATE_COURSE_ID);
            for (String id : urId) {
                urCeAuyEntity.setUrId(Integer.parseInt(id));
                urCeAuyMapper.insert(urCeAuyEntity);
            }
        }
    }

    public void deleteRole(Integer ceId, Integer urId) {
        UrCeAuyEntity urCeAuyEntity = new UrCeAuyEntity();
        urCeAuyEntity.setCeId(ceId);
        urCeAuyEntity.setUrId(urId);
        urCeAuyMapper.deleteForRealWithExample(urCeAuyEntity);

        //update application status
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setCeId(ceId);
        applicationEntity.setUrId(urId);
        List<ApplicationEntity> lstApply = applicationMapper.selectWithExample(applicationEntity);
        if (lstApply != null) {
            for (ApplicationEntity apply : lstApply) {
                if (apply.getStatus().equals(Constants.APPLICATION_STATUS_ACCEPTED)) {
                    apply.setStatus(Constants.APPLICATION_STATUS_DENIED);
                    setUpdateInfo(apply);
                    setCreateInfo(apply);
                    applicationMapper.updateNotNullByPK(apply);
                }
            }
        }

        //send notification
        NotificationEntity notificationEntity = new NotificationEntity();
        setCreateInfo(notificationEntity);
        setUpdateInfo(notificationEntity);
        sendKickNotification(ceId, urId, notificationEntity);
    }

    @Transactional
    public void addMaterialToCourse(String[] mlId, Integer ceId) {
        CeMlEntity ceMlEntity = new CeMlEntity();
        setUpdateInfo(ceMlEntity);
        setCreateInfo(ceMlEntity);
        ceMlEntity.setCeId(ceId);
        ceMlEntity.setIsHidden(Constants.NOT_HIDDEN_FLAG);
        for (String id : mlId) {
            Integer materialId = Integer.parseInt(id);
            ceMlEntity.setMlId(materialId);
            int recordCnt = ceMlMapper.insert(ceMlEntity);
            if (recordCnt == 0) {
                rollBack(Constants.MSG_INSERT_ERR);
            }
        }
    }

    @Transactional
    public void doApply(Integer ceId, Integer urId) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setCeId(ceId);
        applicationEntity.setUrId(urId);
        List<ApplicationEntity> lstApply = applicationMapper.selectWithExample(applicationEntity);

        //check if this user already apply to this course or not
        for (ApplicationEntity apply : lstApply) {
            if (apply.getStatus().equals(Constants.APPLICATION_STATUS_ACCEPTED)) {
                rollBack(Constants.MSG_ALREADY_APPLIED_ERR);
            }
            if (apply.getStatus().equals(Constants.APPLICATION_STATUS_PENDING)) {
                rollBack(Constants.MSG_APPLY_PENDING_ERR);
            }
        }

        //insert application
        applicationEntity.setStatus(Constants.APPLICATION_STATUS_PENDING);
        setCreateInfo(applicationEntity);
        setUpdateInfo(applicationEntity);
        applicationMapper.insert(applicationEntity);

        NotificationEntity notificationEntity = new NotificationEntity();
        setCreateInfo(notificationEntity);
        setUpdateInfo(notificationEntity);
        sendApplyNotification(ceId, urId, notificationEntity);
    }

    public int countPendingApplication(Integer ceId) {
        return courseMapper.countApplication(ceId);
    }

    public List<ApplicationDTO> getPendingApplication(Integer ceId) {
        List<ApplicationDTO> lstApply = applicationMapper.getAllApplicationInCourse(ceId);

        return lstApply;
    }

    @Transactional
    public void acceptApplication(Integer urId, Integer ceId) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setUrId(urId);
        applicationEntity.setCeId(ceId);
        applicationEntity.setStatus(Constants.APPLICATION_STATUS_PENDING);
        List<ApplicationEntity> lstApply = applicationMapper.selectWithExample(applicationEntity);
        if (null == lstApply || lstApply.size() != 1) {
            rollBack(Constants.MSG_UPDATE_ERR);
        }
        applicationEntity = lstApply.get(0);
        applicationEntity.setStatus(Constants.APPLICATION_STATUS_ACCEPTED);
        setUpdateInfo(applicationEntity);
        applicationMapper.updateNotNullByPK(applicationEntity);


        //add to course's role list
        UrCeAuyEntity urCeAuyEntity = new UrCeAuyEntity();
        urCeAuyEntity.setAuyId(Constants.AUTH_PARTICIPATE_COURSE_ID);
        urCeAuyEntity.setUrId(urId);
        urCeAuyEntity.setCeId(ceId);
        setCreateInfo(urCeAuyEntity);
        setUpdateInfo(urCeAuyEntity);
        urCeAuyMapper.insert(urCeAuyEntity);

        //send notification to course's administrators
        NotificationEntity notificationEntity = new NotificationEntity();
        setCreateInfo(notificationEntity);
        setUpdateInfo(notificationEntity);

        //send notification
        sendAcceptNotification(ceId, urId, notificationEntity);
    }

    @Transactional
    public void declineApplication(Integer urId, Integer ceId) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setUrId(urId);
        applicationEntity.setCeId(ceId);
        applicationEntity.setStatus(Constants.APPLICATION_STATUS_PENDING);
        List<ApplicationEntity> lstApply = applicationMapper.selectWithExample(applicationEntity);
        if (null == lstApply || lstApply.size() != 1) {
            rollBack(Constants.MSG_UPDATE_ERR);
        }
        applicationEntity = lstApply.get(0);
        applicationEntity.setStatus(Constants.APPLICATION_STATUS_DENIED);
        setUpdateInfo(applicationEntity);
        applicationMapper.updateNotNullByPK(applicationEntity);

        //add notification
        NotificationEntity notificationEntity = new NotificationEntity();
        setCreateInfo(notificationEntity);
        setUpdateInfo(notificationEntity);

        //send notification
        sendDeclineNotification(ceId, urId, notificationEntity);
    }


    @Async
    public void sendApplyNotification(Integer ceId, Integer urId, NotificationEntity notificationEntity) {
        //send notification to course's administrators
        //get course infor
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);

        //get application sender infor
        UserDTO sender = userMapper.findUserById(urId);

        //get course's administrator infor
        UserDTO receiver = userMapper.findUserByHandle(courseEntity.getCreateUser());

        //set notification infor
        notificationEntity.setType(Constants.NOTIFICATION_APPLICATION_PENDING_TYPE);
        String content = StringUtil.buildString(StringUtil.makeTextBoldInHTML(sender.getFullname()), Constants.NOTIFICATION_APPLICATION_PENDING_CONTENT, StringUtil.makeTextBoldInHTML(courseEntity.getName()));
        notificationEntity.setContent(content);
        notificationEntity.setUrId(receiver.getId());
        notificationEntity.setLink(StringUtil.buildString("/course/", courseEntity.getId().toString(), "/role"));
        notificationEntity.setIsRead(Constants.MESSAGE_NOT_READ_FLAG);
        notificationMapper.insert(notificationEntity);

        //send message to view
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.NOTIFICATION_TOPIC + receiver.getId(), notificationEntity);
    }

    @Async
    public void sendAcceptNotification(Integer ceId, Integer urId, NotificationEntity notificationEntity) {
        //send notification to student
        //get course infor
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);

        //set notification infor
        notificationEntity.setType(Constants.NOTIFICATION_APPLICATION_ACCEPTED_TYPE);
        String content = StringUtil.buildString(Constants.NOTIFICATION_APPLICATION_ACCEPTED_CONTENT, StringUtil.makeTextBoldInHTML(courseEntity.getName()));
        notificationEntity.setContent(content);
        notificationEntity.setUrId(urId);
        notificationEntity.setLink(StringUtil.buildString("/course/", ceId.toString()));
        notificationEntity.setIsRead(Constants.MESSAGE_NOT_READ_FLAG);
        notificationMapper.insert(notificationEntity);

        //send message to view
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.NOTIFICATION_TOPIC + urId, notificationEntity);
    }

    @Async
    public void sendKickNotification(Integer ceId, Integer urId, NotificationEntity notificationEntity) {
        //send notification to student
        //get course infor
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);

        //set notification infor
        notificationEntity.setType(Constants.NOTIFICATION_COURSE_KICKED_TYPE);
        String content = StringUtil.buildString(Constants.NOTIFICATION_COURSE_KICKED_CONTENT, StringUtil.makeTextBoldInHTML(courseEntity.getName()));
        notificationEntity.setContent(content);
        notificationEntity.setUrId(urId);
        notificationEntity.setLink(StringUtil.buildString("/course/", ceId.toString()));
        notificationEntity.setIsRead(Constants.MESSAGE_NOT_READ_FLAG);
        notificationMapper.insert(notificationEntity);

        //send message to view
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.NOTIFICATION_TOPIC + urId, notificationEntity);
    }

    @Async
    public void sendDeclineNotification(Integer ceId, Integer urId, NotificationEntity notificationEntity) {
        //send notification to course's administrators
        //get course infor
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);

        //set notification infor
        notificationEntity.setType(Constants.NOTIFICATION_APPLICATION_DENIED_TYPE);
        String content = StringUtil.buildString(Constants.NOTIFICATION_APPLICATION_DENIED_CONTENT, StringUtil.makeTextBoldInHTML(courseEntity.getName()));
        notificationEntity.setContent(content);
        notificationEntity.setUrId(urId);
        notificationEntity.setLink(StringUtil.buildString("/course/", ceId.toString()));
        notificationEntity.setIsRead(Constants.MESSAGE_NOT_READ_FLAG);
        notificationMapper.insert(notificationEntity);

        //send message to view
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.NOTIFICATION_TOPIC + urId, notificationEntity);
    }

    public CourseDTO getInformation(int ceId) {
        //get course information
        CourseDTO courseDTO = new CourseDTO();
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);

        //get course's creator
        UserDTO user = userMapper.findUserByHandle(courseEntity.getCreateUser());

        courseDTO.setCreator(user);

        //set course start time and end time
        modelMapper.map(courseEntity, courseDTO);
        Date startTime = courseEntity.getStartTime();
        Date endTime = courseEntity.getEndTime();
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        courseDTO.setStartTime(sdfr.format(startTime));
        courseDTO.setEndTime(sdfr.format(endTime));
        return courseDTO;
    }

    public List<ProblemDTO> getProblemToDisplay(Integer ceId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean getAllProblem = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (courseValidator.checkPermission(auth, ceId, Constants.AUTH_EDIT_COURSE_TEXT)) {
                getAllProblem = true;
            }
        }
        List<ProblemDTO> lst = problemMapper.getProblemToDisplayInCourse(ceId, getAllProblem);
        int cnt = 0;
        for (ProblemDTO problemDTO : lst) {
            if (problemDTO.getIsHidden() == 0) {
                problemDTO.setAlias(++cnt);
            } else {
                problemDTO.setAlias(-1);
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (problemDTO.getLstTag() != null) {
                List<TagDTO> lstTag = problemDTO.getLstTag();
                for (int i = 0; i < lstTag.size(); ++i) {
                    stringBuilder.append(lstTag.get(i).getName());
                    if (i < lstTag.size() - 1) {
                        stringBuilder.append(",");
                    }
                }
            }
            problemDTO.setTag(stringBuilder.toString());
            if (Constants.BLANK.equals(stringBuilder.toString())) {
                problemDTO.setTag(null);
            }
        }
        return lst;
    }

    public void setProblemHiddenStatus(Integer ceId, Integer pmId, Integer status) {
        CePmEntity cePmEntity = new CePmEntity();
        cePmEntity.setCeId(ceId);
        cePmEntity.setPmId(pmId);
        cePmEntity.setIsHidden(status);
        setUpdateInfo(cePmEntity);
        cePmMapper.updateNotNullByPK(cePmEntity);
    }

    public List<ProblemDTO> getProblemToAdd(int ceId) {
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        List<ProblemDTO> lst = problemMapper.getProblemForCourse(urId, Constants.AUTH_VIEW_PROBLEM_ID, ceId);
        for (ProblemDTO problemDTO : lst) {
            StringBuilder stringBuilder = new StringBuilder();
            List<TagDTO> lstTag = problemDTO.getLstTag();
            for (int i = 0; i < lstTag.size(); ++i) {
                stringBuilder.append(lstTag.get(i).getName());
                if (i < lstTag.size() - 1) {
                    stringBuilder.append(",");
                }
            }
            problemDTO.setTag(stringBuilder.toString());
            if (Constants.BLANK.equals(stringBuilder.toString())) {
                problemDTO.setTag(null);
            }
        }
        return lst;
    }

    public List<ProblemDTO> getProblemToSubmit(Integer ceId) {
        List<ProblemDTO> lst = problemMapper.getProblemToSubmitInCourse(ceId);
        int cnt = 0;
        for (ProblemDTO problemDTO : lst) {
            String name = ++cnt + ". " + problemDTO.getName();
            problemDTO.setName(name);
        }
        return lst;
    }

    @Transactional
    public void addProblemToCourse(Integer ceId, String[] lstPmId) throws Exception {
        for (String pmId : lstPmId) {
            CePmEntity cePmEntity = new CePmEntity();
            cePmEntity.setCeId(ceId);
            cePmEntity.setPmId(Integer.parseInt(pmId));
            cePmEntity.setIsHidden(0);
            setUpdateInfo(cePmEntity);
            setCreateInfo(cePmEntity);
            cePmMapper.insert(cePmEntity);

            //reset firstSolve time
            problemMapper.resetFirstSolveTime(Integer.parseInt(pmId));
        }
    }

    public List<SubmissionDTO> getSubmissionInCourse(Integer ceId, boolean getAll) {
        List<SubmissionDTO> lst = null;
        if (getAll) {
            lst = submissionMapper.getSubmissionInCourse(ceId, null);
        } else {
            String handle = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
            lst = submissionMapper.getSubmissionInCourse(ceId, handle);
        }
        for (SubmissionDTO submissionDTO : lst) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String strDate = dateFormat.format(submissionDTO.getCreateTime());
            submissionDTO.setSubmitTime(strDate);
        }
        return lst;
    }

    @Transactional
    public void doSubmit(String sourceCode, Integer ceId, Integer leId, Integer pmId) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setId(leId);
        languageEntity = languageMapper.selectByPK(languageEntity);
        LanguageDTO languageDTO = new LanguageDTO();
        modelMapper.map(languageEntity, languageDTO);
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setId(pmId);
        problemService.getProblemInfo(problemDTO);
        List<InputDTO> lstInput = inputMapper.getAllTest(pmId);
        problemDTO.setLstInput(lstInput);
        problemDTO.setSourceCode(sourceCode);
        Integer urId = (Integer) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        if (urId == null) {
            rollBack(Constants.MSG_SESSION_TIMEOUT);
        }
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setLeId(languageDTO.getId());
        submissionEntity.setSourceCode(problemDTO.getSourceCode());
        submissionEntity.setPmId(problemDTO.getId());
        submissionEntity.setTimeRun(0);
        submissionEntity.setMemoryUsed(0);
        submissionEntity.setVerdict(Constants.VERDICT_COMPILING);
        submissionEntity.setJudgeStatus(Constants.STATUS_JUDGING);
        submissionEntity.setUrId(urId);
        setUpdateInfo(submissionEntity);
        setCreateInfo(submissionEntity);
        submissionMapper.insertSubmission(submissionEntity);

        CeSnEntity ceSnEntity = new CeSnEntity();
        ceSnEntity.setCeId(ceId);
        ceSnEntity.setSnId(submissionEntity.getId());
        setCreateInfo(ceSnEntity);
        setUpdateInfo(ceSnEntity);
        ceSnMapper.insert(ceSnEntity);

        judgeService.judge(problemDTO, languageDTO, submissionEntity, urId, null, ceId);
    }

    public List<AnnouncementDTO> getAnnouncementListInCourse(Integer ceId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean getAllAnnouncement = false;
        if (null != auth && !StringUtil.isNull(auth.getName())) {
            if (courseValidator.checkPermission(auth, ceId, Constants.AUTH_EDIT_COURSE_TEXT)) {
                getAllAnnouncement = true;
            }
        }
        List<AnnouncementDTO> lstAnnounce = announcementMapper.getAnnouncementListInCourse(ceId, getAllAnnouncement);
        for (AnnouncementDTO announce : lstAnnounce) {
            if (announce.getProblem().getId() != null && announce.getProblem().getId().equals(0)) {
                announce.getProblem().setName("Thông báo chung");
            }
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            announce.setTimePosted(sdfr.format(announce.getUpdateTime()));
        }
        return lstAnnounce;
    }

    @Transactional
    public void addQuestion(Integer ceId, Integer pmId, String question) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setPmId(pmId);
        announcementEntity.setQuestion(question);
        announcementEntity.setIsAnswered(0);
        announcementEntity.setIsHidden(0);
        announcementEntity.setIsFromCreator(0);
        setUpdateInfo(announcementEntity);
        setCreateInfo(announcementEntity);
        announcementMapper.insertAnnouncement(announcementEntity);

        CeAtEntity ceAtEntity = new CeAtEntity();
        ceAtEntity.setCeId(ceId);
        ceAtEntity.setAtId(announcementEntity.getId());
        setCreateInfo(ceAtEntity);
        setUpdateInfo(ceAtEntity);
        ceAtMapper.insert(ceAtEntity);
    }

    @Transactional
    public void addAnnouncement(Integer ceId, Integer pmId, String answer) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setPmId(pmId);
        announcementEntity.setAnswer(answer);
        announcementEntity.setIsAnswered(1);
        announcementEntity.setIsHidden(0);
        announcementEntity.setIsFromCreator(1);
        setUpdateInfo(announcementEntity);
        setCreateInfo(announcementEntity);
        announcementMapper.insertAnnouncement(announcementEntity);

        CeAtEntity ceAtEntity = new CeAtEntity();
        ceAtEntity.setCeId(ceId);
        ceAtEntity.setAtId(announcementEntity.getId());
        setCreateInfo(ceAtEntity);
        setUpdateInfo(ceAtEntity);
        ceAtMapper.insert(ceAtEntity);

        sendAnswerNotification(announcementEntity, ceId);
    }

    public void changeAnnounceHiddenState(Integer atId, Integer newState) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setId(atId);
        announcementEntity.setIsHidden(newState);
        setUpdateInfo(announcementEntity);
        announcementEntity.setDeleteFlg("0");
        int recordCnt = announcementMapper.updateNotNullByPK(announcementEntity);
        if (0 == recordCnt) {
            rollBack(Constants.MSG_UPDATE_ERR);
        }
    }

    public String getQuestion(Integer atId) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setId(atId);
        announcementEntity.setDeleteFlg("0");
        announcementEntity = announcementMapper.selectByPK(announcementEntity);
        if (announcementEntity == null) {
            throw new NoSuchPageException("Announcement not found!");
        }
        return announcementEntity.getQuestion();
    }

    public void answerQuestion(Integer atId, String answer, Integer ceId) {
        AnnouncementEntity announcementEntity = new AnnouncementEntity();
        announcementEntity.setId(atId);
        announcementEntity.setIsAnswered(1);
        announcementEntity.setAnswer(answer);
        setUpdateInfo(announcementEntity);
        announcementEntity.setDeleteFlg("0");
        announcementMapper.updateNotNullByPK(announcementEntity);
        sendAnswerNotification(announcementEntity, ceId);
    }

    @Async
    public void sendAnswerNotification(AnnouncementEntity announcementEntity, Integer ceId) {
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.COURSE_ANNOUNCEMENT_TOPIC + ceId, announcementEntity);
    }

    //submission in contest
    public SubmissionDTO getSubmitDetail(int snId, Integer ceId) {
        SubmissionDTO submissionDTO = generalService.getSubmitDetail(snId);
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);
        CourseDTO course = new CourseDTO();
        course.setId(ceId);
        course.setName(courseEntity.getName());
        submissionDTO.setCourseDTO(course);
        return submissionDTO;
    }

    public CourseDTO getCourseInfo(int ceId) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);
        CourseDTO course = modelMapper.map(courseEntity, CourseDTO.class);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        course.setStartTime(dateFormat.format(courseEntity.getStartTime()));
        course.setEndTime(dateFormat.format(courseEntity.getEndTime()));
        return course;
    }

    public List<ProblemDTO> getProblemForLeaderboard(int ceId) {
        List<ProblemDTO> lstProblem = problemMapper.getProblemForLeaderboardInCourse(ceId);
        for (ProblemDTO problem : lstProblem) {
            int ac = problem.getSolveCnt();
            int all = problem.getTotalSubmission();
            if (all == 0 || ac == 0) {
                problem.setSolvePercentage("(0%)");
                continue;
            }
            NumberFormat df = new DecimalFormat("#.00");
            double percentage = (double) 100 * ac / all;
            problem.setSolvePercentage("(" + df.format(percentage) + "%)");
        }
        return lstProblem;
    }

    //get leaderboard information here
    public List<UserDTO> getLeaderboardInfor(Integer ceId) {

        //1 user -> many problems
        List<UserDTO> lstUser = userMapper.getLeaderboardInformationForCourse(ceId, Constants.AUTH_PARTICIPATE_COURSE_ID, Constants.STATUS_ACCEPTED);
        for (UserDTO user : lstUser) {
            int score = 0;
            int penalty = 0;

            long start = user.getContestStartTime().getTime();
            /**
             * 1 problem -> many submission
             * however, we only need to calculate number of submissions and time penalty up to the first accepted one.
             * which also means the first one to get all the correct answers in all tests
             */
            for (ProblemDTO problem : user.getLstProblem()) {
                int isSolved = 0;
                int time = 0;
                String solveTime = "--:--";
                problem.setIsFirstSolve(0);
                if (null != problem.getLstSubmission()) {
                    problem.setCorrectAns(0);
                    for (SubmissionDTO submit : problem.getLstSubmission()) {

                        //we will ignore any submissions submitted after the first AC-ed one.
                        if (0 == isSolved) {
                            if (submit.getCorrectAns() != null && submit.getCorrectAns().equals(problem.getTestCnt())) {
                                isSolved = 1;

                                //calculate submit time if AC-ed
                                long current = submit.getCreateTime().getTime();
                                long elapsed = current - start;
                                if (elapsed > 0) {
                                    int minutes = (int) Math.floor((elapsed / 1000 / 60) % 60);
                                    int hours = (int) Math.floor(elapsed / (1000 * 60 * 60));
                                    String h = StringUtils.leftPad(String.valueOf(hours), 2, "0");
                                    String m = StringUtils.leftPad(String.valueOf(minutes), 2, "0");
                                    solveTime = h + ":" + m;

                                    time += (int) Math.floor((elapsed / 1000 / 60));
                                }else{
                                    solveTime = "00:00";
                                }
                            } else {
                                time += Constants.SUBMISSION_FAIL_PENALTY;
                            }

                            //if not AC-ed then add penalty
                            if (submit.getCorrectAns() != null && submit.getCorrectAns().compareTo(problem.getCorrectAns()) > 0) {
                                problem.setCorrectAns(submit.getCorrectAns());
                            }
                        }
                    }
                }

                //score = number of AC-ed problems
                score += isSolved;

                //calculate penalty only when AC-ed
                if (isSolved == 1) {
                    penalty += time;
                }
                problem.setIsSolved(isSolved);
                problem.setSolveTime(solveTime);
            }
            user.setScore(score);
            user.setPenalty(penalty);
        }
        Collections.sort(lstUser, new ScoreboardComparator());
        return lstUser;
    }

    //first we sort desc by score, then we sort asc by time penalty
    class ScoreboardComparator implements Comparator<UserDTO> {

        @Override
        public int compare(UserDTO o1, UserDTO o2) {
            if (o1.getScore().equals(o2.getScore())) {
                return o1.getPenalty().compareTo(o2.getPenalty());
            }
            return o2.getScore().compareTo(o1.getScore());
        }
    }

    public CourseListVO getAllCourse(){
        List<CourseDTO> lstCourse = courseMapper.getAllCourse(Constants.AUTH_PARTICIPATE_COURSE_ID);
        List<CourseDTO> ongoingLst = new ArrayList<>();
        List<CourseDTO> pastLst = new ArrayList<>();
        CourseListVO courseListVO = new CourseListVO();
        for(CourseDTO course : lstCourse){

            //set preview
            if (!StringUtil.isNull(course.getDescription()) && course.getDescription().length() > Constants.MAX_DESCRIPTION_LENGTH) {
                course.setPreview(StringUtil.getFirstPartOfString(course.getDescription(), Constants.MAX_DESCRIPTION_LENGTH) + "...");
            } else {
                course.setPreview(course.getDescription());
            }

            //check if course finished or not
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            course.setStartTime(dateFormat.format(course.getStart()));
            course.setEndTime(dateFormat.format(course.getEnd()));
            Date now = new Date();
            if (now.compareTo(course.getEnd()) > 0){
                pastLst.add(course);
            }else{
                ongoingLst.add(course);
            }
        }
        courseListVO.setOngoingLst(ongoingLst);
        courseListVO.setPastLst(pastLst);
        return courseListVO;

    }

    public List<CourseDTO> getCourseForCurrentUser(){
        Integer currentUserID = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        List<CourseDTO> lstCourse = courseMapper.getCourseForUser(Constants.AUTH_PARTICIPATE_COURSE_ID,currentUserID);
        for(CourseDTO course : lstCourse){
            //set preview
            if (!StringUtil.isNull(course.getDescription()) && course.getDescription().length() > Constants.MAX_DESCRIPTION_LENGTH) {
                course.setPreview(StringUtil.getFirstPartOfString(course.getDescription(), Constants.MAX_DESCRIPTION_LENGTH) + "...");
            } else {
                course.setPreview(course.getDescription());
            }
        }
        return lstCourse;
    }
}

package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.CourseDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.*;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 14/03/2018
 * Purpose:
 */
@Service
public class CourseService extends BaseService {

    @Autowired
    private MaterialMapper materialMapper;

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
    private CeMlMapper ceMlMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private NotificationMapper notificationMapper;

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

    @Transactional
    public int createCourse(CourseDTO courseDTO) throws ParseException {
        CourseEntity courseEntity = new CourseEntity();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        courseEntity.setStartTime(formatter.parse(courseDTO.getStartTime()));
        courseEntity.setEndTime(formatter.parse(courseDTO.getEndTime()));
        courseEntity.setName(courseDTO.getName());
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
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setType(Constants.NOTIFICATION_APPLICATION_PENDING_TYPE);
        String content = StringUtil.buildString(StringUtil.makeTextBoldInHTML(sender.getFullname()), Constants.NOTIFICATION_APPLICATION_PENDING_CONTENT, StringUtil.makeTextBoldInHTML(courseEntity.getName()));
        notificationEntity.setContent(content);
        notificationEntity.setUrId(receiver.getId());
        notificationEntity.setLink(StringUtil.buildString("/course/", courseEntity.getId().toString(), "/role"));
        notificationEntity.setIsRead(Constants.MESSAGE_NOT_READ_FLAG);
        setCreateInfo(notificationEntity);
        setUpdateInfo(notificationEntity);
        notificationMapper.insert(notificationEntity);

        sendApplyNotification(notificationEntity, receiver.getId());
    }

    public int countPendingApplication(Integer ceId) {
        return courseMapper.countApplication(ceId);
    }

    @Async
    public void sendApplyNotification(NotificationEntity notificationEntity, Integer receiverId) {
        simpMessagingTemplate.convertAndSend(Constants.WEB_SOCKET_PREFIX + Constants.NOTIFICATION_TOPIC + receiverId, notificationEntity);
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
}

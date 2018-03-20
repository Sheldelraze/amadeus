package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.CourseDTO;
import com.minh.nguyen.dto.UserDTO;
import com.minh.nguyen.entity.CeMlEntity;
import com.minh.nguyen.entity.CourseEntity;
import com.minh.nguyen.entity.UrCeAuyEntity;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
import com.minh.nguyen.mapper.*;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void doApply(Integer ceId, Integer urId) {

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

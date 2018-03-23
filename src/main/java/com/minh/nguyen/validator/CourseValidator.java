package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.entity.ApplicationEntity;
import com.minh.nguyen.entity.CourseEntity;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.mapper.ApplicationMapper;
import com.minh.nguyen.mapper.AuthorityMapper;
import com.minh.nguyen.mapper.CourseMapper;
import com.minh.nguyen.mapper.SubmissionMapper;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 14/03/2018
 * Purpose:
 */
@Component("CourseValidator")
public class CourseValidator extends BaseValidator {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ApplicationMapper applicationMapper;

    @Autowired
    private SubmissionMapper submissionMapper;

    public boolean checkApplyPermission(Integer ceId, Integer urId) {
        if (urId == null || ceId == null) {
            return false;
        }
        if (!checkRole(Constants.ROLE_STUDENT_TEXT)) {
            return false;
        }
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setCeId(ceId);
        applicationEntity.setUrId(urId);
        List<ApplicationEntity> lstApply = applicationMapper.selectWithExample(applicationEntity);
        for (ApplicationEntity apply : lstApply) {
            if (apply.getStatus().equals(Constants.APPLICATION_STATUS_ACCEPTED)) {
                return false;
            }
            if (apply.getStatus().equals(Constants.APPLICATION_STATUS_PENDING)) {
                return false;
            }
        }
        return true;
    }

    //check if curent user has any authority in current contest
    public boolean checkPermission(Authentication auth, Integer ceId, String... authority) throws NoSuchPageException {
        List<AuthorityDTO> lstAuthority = authorityMapper.getCourseAuthority(ceId, auth.getName());
        for (AuthorityDTO curAuth : lstAuthority) {
            for (String requireAuth : authority) {
                if (requireAuth.equals(curAuth.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkViewSubmissionPermission(Authentication auth,Integer snId,Integer ceId){
        List<AuthorityDTO> lstAuthority = authorityMapper.getCourseAuthority(ceId, auth.getName());
        boolean flag = false;
        for (AuthorityDTO curAuth : lstAuthority) {
            if (curAuth.getId().equals(Constants.AUTH_VIEW_COURSE_ID)) {
                return true;
            }
            if (curAuth.getId().equals(Constants.AUTH_PARTICIPATE_COURSE_ID)) {
                flag = true;
            }
        }
        if (!flag){
            return false;
        }

        CourseEntity courseEntity = getCourseById(ceId);
        if (courseEntity.getShowSubmit().equals(Constants.SHOW_SUBMIT_ALL)){
            return true;
        }

        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setId(snId);
        List<SubmissionEntity> lstSubmission = submissionMapper.selectWithExample(submissionEntity);
        if (lstSubmission.size() != 1){
            throw new NoSuchPageException("Submission not found!");
        }

        //check if submission is from current user
        submissionEntity = lstSubmission.get(0);
        Integer currentUserId = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ID);
        if (submissionEntity.getUrId().equals(currentUserId)){
            return true;
        }

        //else check current user has solved this problem and this contests allow to view solved problem's solutions
        if (courseEntity.getShowSubmit().equals(Constants.SHOW_SUBMIT_SOLVED)){
            Integer solveCnt = submissionMapper.checkSolvedStatusInContest(ceId,submissionEntity.getPmId(),submissionEntity.getUrId());
            return solveCnt > 0;
        }

        return false;
    }

    public boolean checkRole(String roleName) {
        Object currentRole = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_NAME);
        return currentRole != null && currentRole.toString().equals(roleName);
    }

    public boolean checkParticipate(Authentication auth, Integer ctId) throws NoSuchPageException {
        List<AuthorityDTO> lstAuthority = authorityMapper.getCourseAuthority(ctId, auth.getName());
        for (AuthorityDTO curAuth : lstAuthority) {
            if (curAuth.getId().equals(Constants.AUTH_PARTICIPATE_COURSE_ID)) {
                return true;
            }
        }

        //not participator
        return false;
    }

    //Check if user is the contest's creator
    public boolean checkCreator(Authentication authentication, Integer ceId) {
        CourseEntity courseEntity = getCourseById(ceId);
        return courseEntity.getCreateUser().equals(authentication.getName());
    }

    @Override
    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    @Override
    public void validateLogic(BaseForm clazz, BindingResult errors) {

    }

    private CourseEntity getCourseById(int ceId) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(ceId);
        courseEntity = courseMapper.selectByPK(courseEntity);
        if (null == courseEntity) {
            throw new NoSuchPageException("Contest not found!");
        }
        return courseEntity;
    }
}

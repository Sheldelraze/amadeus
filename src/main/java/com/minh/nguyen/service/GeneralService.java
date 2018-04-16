package com.minh.nguyen.service;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.StudentDTO;
import com.minh.nguyen.dto.SubmissionDTO;
import com.minh.nguyen.dto.SubmitDetailDTO;
import com.minh.nguyen.entity.BaseEntity;
import com.minh.nguyen.entity.NotificationEntity;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.mapper.NotificationMapper;
import com.minh.nguyen.mapper.SubmissionMapper;
import com.minh.nguyen.mapper.UserMapper;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 27/01/2018
 * Purpose:
 */
@Service
public class GeneralService extends BaseService {

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession httpSession;

    public List<SubmissionDTO> getSubmission() {
        List<SubmissionDTO> lstSubmission = submissionMapper.getSubmission();
        for (SubmissionDTO submissionDTO : lstSubmission) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String strDate = dateFormat.format(submissionDTO.getCreateTime());
            submissionDTO.setSubmitTime(strDate);
        }
        return lstSubmission;
    }

    public void removeNotification(Integer urId) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUrId(urId);
        notificationEntity.setIsRead(Constants.MESSAGE_NOT_READ_FLAG);
        List<NotificationEntity> lstNotify = notificationMapper.selectWithExample(notificationEntity);
        for (NotificationEntity notify : lstNotify) {
            notify.setIsRead(Constants.MESSAGE_READ_FLAG);
            setUpdateInfo(notify);
            notificationMapper.updateNotNullByPK(notify);
        }
    }

    //normal submission
    public SubmissionDTO getSubmitDetail(int snId) {
        List<SubmissionDTO> lstSubmit = submissionMapper.getSubmitDetail(snId);
        if (CollectionUtils.isEmpty(lstSubmit)){
            throw  new NoSuchPageException("No submission found!");
        }
        SubmissionDTO submit = lstSubmit.get(0);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String strDate = dateFormat.format(submit.getCreateTime());
        submit.setSubmitTime(strDate);
        List<SubmitDetailDTO> lst = submit.getLstSubmitDetail();
        for (int i = 0; i < lst.size(); i++) {
            SubmitDetailDTO detail = lst.get(i);
            detail.setInput(detail.getInput());
            detail.setOutput(detail.getOutput());
            detail.setAnswer(detail.getAnswer());
            String res = detail.getResult();
            if (null != res) {
                res = res.replaceAll(" ", "&ensp;");
                detail.setResult(res);
            }
        }

        //check if current user can edit submission
        Object currentUserHandle = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        submit.setCanEditSubmission(false);
        if (!StringUtil.isNull(currentUserHandle)){
            if (submit.getCreateUser().equals(currentUserHandle.toString())){
                submit.setCanEditSubmission(true);
            }
        }
        return submit;
    }

    void setCreateInfo(BaseEntity entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setCreateClass(GeneralService.class.getName());
        entity.setCreateTime(time);
        entity.setCreateUser(currentUser);
        entity.setDeleteFlg("0");
    }

    void setUpdateInfo(BaseEntity entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Calendar today = Calendar.getInstance();
        Date time = today.getTime();
        entity.setUpdateClass(GeneralService.class.getName());
        entity.setUpdateTime(time);
        entity.setUpdateUser(currentUser);
    }

    public List<StudentDTO> getListUserRank(){
        List<StudentDTO> lstUser = userMapper.getTopUser(Constants.ROLE_STUDENT_ID);

        return lstUser;
    }

    public void changeSubmissionPublicState(Integer snId,Integer newState){
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setId(snId);
        submissionEntity = submissionMapper.selectByPK(submissionEntity);
        if (submissionEntity == null){
            throw new NoSuchPageException("Submission not found!");
        }
        //check if current user can edit submission
        Object currentUserHandle = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        if (currentUserHandle == null || !submissionEntity.getCreateUser().equals(currentUserHandle.toString())){
            rollBack(Constants.MSG_NOT_ALLOWED_ERR);
        }

        submissionEntity.setIsPublic(newState);
        int recordCnt = submissionMapper.updateNotNullByPK(submissionEntity);
        if (recordCnt == 0){
            rollBack(Constants.MSG_SYSTEM_ERR);
        }
    }
}

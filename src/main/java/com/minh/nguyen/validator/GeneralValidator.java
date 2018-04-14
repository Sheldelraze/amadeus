package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.entity.SubmissionEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.mapper.SubmissionMapper;
import com.minh.nguyen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author Mr.Minh
 * @since 14/04/2018
 * Purpose:
 */
@Component("GeneralValidator")
public class GeneralValidator {

    @Autowired
    private SubmissionMapper submissionMapper;

    @Autowired
    private HttpSession httpSession;

    private SubmissionEntity getSubmissionByID(Integer snId){
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setId(snId);
        submissionEntity.setDeleteFlg(Constants.NOT_DELETE_FLAG);
        submissionEntity = submissionMapper.selectByPK(submissionEntity);
        if (submissionEntity == null){
            throw  new NoSuchPageException("No such submission!");
        }
        return submissionEntity;
    }

    public boolean checckSubmissionViewAuth(Integer snId){
        //first we check if this submission existed
        SubmissionEntity submissionEntity = getSubmissionByID(snId);

        //then if this submission is public (anyone can view) then return true
        if (submissionEntity.getIsPublic().equals(Constants.PUBLIC_FLAG)){
            return true;
        }

        //if current user not authenticated then return false
        Object currentLoginUserHandle = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        if (StringUtil.isNull(currentLoginUserHandle)){
            return false;
        }

        //else check if current user is the creator
        String handle = currentLoginUserHandle.toString();
        return handle.equals(submissionEntity.getCreateUser());
    }
}

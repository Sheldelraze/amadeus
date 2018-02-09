package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.exception.InputCheckException;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.form.contest.ContestSubmitForm;
import com.minh.nguyen.mapper.AuthorityMapper;
import com.minh.nguyen.mapper.ContestMapper;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 07/02/2018
 * Purpose:
 */
@Component("ContestValidator")
public class ContestValidator extends BaseValidator {

    @Autowired
    private ContestMapper contestMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    //check if user outside contest can view problem (only work when contest is public)
    public boolean checkPublic(Integer ctId) throws NoSuchPageException {
        ContestEntity contestEntity = getContestById(ctId);
        return contestEntity.getIsAnyoneCanParticipate().equals(1)
                && contestEntity.getShowInforToAll().equals(1);
    }

    //check if curent user has any authority in current contest
    public boolean checkPermission(Authentication auth, Integer ctId, String... authority) throws NoSuchPageException {
        List<AuthorityDTO> lstAuthority = authorityMapper.getContestAuthority(ctId, auth.getName());
        for(AuthorityDTO curAuth : lstAuthority){
            for(String requireAuth : authority){
                if (requireAuth.equals(curAuth.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    //if contest is public, outsider can view contest's information only when contest has started
    public boolean checkOutsiderPermission(Authentication authentication,Integer ctId){
        ContestEntity contestEntity = getContestById(ctId);
        if (!contestEntity.getIsAnyoneCanParticipate().equals(1) || !contestEntity.getShowInforToAll().equals(1)){
            return false;
        }
        Date currentTime = new Date();
        Date startTime = contestEntity.getStartTime();
        List<AuthorityDTO> lstAuthority = authorityMapper.getContestAuthority(ctId, authentication.getName());
        for (AuthorityDTO curAuth : lstAuthority) {
            if (curAuth.getId().equals(Constants.AUTH_PARTICIPATE_CONTEST)) {
                return false;
            }
        }
        return currentTime.compareTo(startTime) >= 0;
    }

    //Check if user is the contest's creator
    public boolean checkCreator(Authentication authentication,Integer ctId){
        ContestEntity contestEntity = getContestById(ctId);
        return contestEntity.getCreateUser().equals(authentication.getName());
    }
    private  ContestEntity getContestById(int ctId){
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        if (null == contestEntity){
            throw new NoSuchPageException("Contest not found!");
        }
        return contestEntity;
    }

    //check if user is participator, then allow to view only when contest is started
    //else if normal user allow when contest has finished
    public boolean checkParticipate(Authentication auth, Integer ctId) throws NoSuchPageException {
        ContestEntity contestEntity = getContestById(ctId);
        Date currentTime = new Date();
        Date startTime = contestEntity.getStartTime();
        Date endTime = DateUtils.addMinutes(startTime, contestEntity.getDuration());
        List<AuthorityDTO> lstAuthority = authorityMapper.getContestAuthority(ctId, auth.getName());
        for (AuthorityDTO curAuth : lstAuthority) {
            if (curAuth.getId().equals(Constants.AUTH_PARTICIPATE_CONTEST)) {
                return currentTime.compareTo(startTime) >= 0;
            }
        }
        return currentTime.compareTo(endTime) > 0
                && contestEntity.getCanPractice().equals(1);
    }
    @Override
    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    @Override
    public void validateLogic(BaseForm clazz, BindingResult errors) {
        if(clazz.getScreenName().equals("submitForm")){
            ContestSubmitForm contestSubmitForm = (ContestSubmitForm)clazz;
            validateSourceCode(contestSubmitForm.getSourceCode(),errors);
        }
    }

    public void validateSourceCode(String source,BindingResult bindingResult){
        final byte[] utf8Bytes;
        try {
            utf8Bytes = source.getBytes("UTF-8");
            if (utf8Bytes.length > 1024 * 256){
                bindingResult.addError(new InputCheckException(
                        Constants.MSG_FILE_TOO_LARGE_ERR,"sourceCode"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

package com.minh.nguyen.validator;

import com.minh.nguyen.dto.AuthorityDTO;
import com.minh.nguyen.entity.ContestEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.mapper.AuthorityMapper;
import com.minh.nguyen.mapper.ContestMapper;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

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

    public boolean checkPermission(Authentication auth, Integer ctId, String authority) throws NoSuchPageException {
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setId(ctId);
        contestEntity = contestMapper.selectByPK(contestEntity);
        if (null == contestEntity){
            throw new NoSuchPageException("Contest not found!");
        }
        List<AuthorityDTO> lstAuthority = authorityMapper.getContestAuthority(ctId, auth.getName());
        for(AuthorityDTO authorityDTO : lstAuthority){
            if(authorityDTO.getName().equals(authority)){
                return true;
            }
        }
        return false;
    }
    @Override
    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    @Override
    public void validateLogic(BaseForm clazz, BindingResult errors) {

    }
}

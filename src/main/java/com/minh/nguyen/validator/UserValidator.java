package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.mapper.UserMapper;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 09/04/2018
 * Purpose:
 */
@Component("UserValidator")
public class UserValidator extends BaseValidator {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession httpSession;

    //select user by ID, throw exception if not found
    public UserEntity getUserByID(Integer urId){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(urId);
        List<UserEntity> lstTemp = userMapper.selectWithExample(userEntity);
        if (CollectionUtils.isEmpty(lstTemp) || lstTemp.size() != 1){
            throw new NoSuchPageException("User not found!");
        }
        return lstTemp.get(0);
    }

    public boolean checkIfCreatorOrAdmin(Authentication parentAuth,Integer childUrID){
        UserEntity userEntity = getUserByID(childUrID);

        //return true if current user (authentication) is the creator of user with ID = childUrID
        if (userEntity.getCreateUser() != null && userEntity.getCreateUser().equals(parentAuth.getName())){
            return true;
        }

        //also return true if current logging in user has ROLE_ADMIN
        Integer currentRoleID = (Integer)httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_ROLE_ID);
        return currentRoleID.equals(Constants.ROLE_ADMIN_ID);
    }
    @Override
    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    @Override
    public void validateLogic(BaseForm clazz, BindingResult errors) {

    }
}

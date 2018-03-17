package com.minh.nguyen.validator;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.entity.MaterialEntity;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.mapper.MaterialMapper;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 17/03/2018
 * Purpose:
 */
@Component("MaterialValidator")
public class MaterialValidator extends BaseValidator {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    private MaterialEntity getMaterialById(int mlId) {
        MaterialEntity materialEntity = new MaterialEntity();
        materialEntity.setId(mlId);
        materialEntity = materialMapper.selectByPK(materialEntity);
        if (null == materialEntity) {
            throw new NoSuchPageException("Contest not found!");
        }
        return materialEntity;
    }

    public boolean checkCreator(Integer mlId) {
        MaterialEntity materialEntity = getMaterialById(mlId);
        Object currentUserHandle = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        return currentUserHandle != null && currentUserHandle.toString().equals(materialEntity.getCreateUser());
    }

    public boolean checkDownloadAuthority(Integer mlId) {
        List<Integer> defaultAuth = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
        if (null != defaultAuth && defaultAuth.contains(Constants.AUTH_VIEW_ALL_MATERIAL_ID)) {
            return true;
        }
        MaterialEntity materialEntity = getMaterialById(mlId);
        Object currentUserHandle = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        if (currentUserHandle != null && currentUserHandle.toString().equals(materialEntity.getCreateUser())) {
            return true;
        }
        return materialEntity.getIsPublic().equals(1);
    }

    @Override
    public void validateLogic(BaseForm clazz, BindingResult errors) {

    }
}

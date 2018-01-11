package com.minh.nguyen.validator;

import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Mr.Minh
 * @since 11/01/2018
 * Purpose:
 */
@Service
public class ProblemValidator extends BaseValidator {

    public void validateField(String fieldName, String fieldValue, BindingResult errors) {

    }

    public void validateLogic(BaseForm clazz, BindingResult errors) {
        if (Objects.equals("createProblem", clazz.getScreenName())) {

        } else if (Objects.equals("makerAdd", clazz.getScreenName())) {

        }
    }

    public void validateCreate(BindingResult errors){

    }
}

package com.minh.nguyen.validator.common;

import com.minh.nguyen.form.BaseForm;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Minh
 * @since 12/01/2018
 * Purpose:
 */
@Service("SimpleValidator")
public class SimpleValidator extends BaseValidator {

    public void validateField(String field, String fieldValue,
            BindingResult errors) {
    }

    public void validateLogic(BaseForm clazz, BindingResult errors) {
        System.out.println("validateLogic!!! ");
    }

}

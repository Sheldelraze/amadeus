package com.minh.nguyen.validator.common;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.exception.InputCheckException;
import com.minh.nguyen.util.CheckUtil;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.annotation.Format;
import com.minh.nguyen.validator.annotation.Number;
import com.minh.nguyen.validator.annotation.MaxLength;
import com.minh.nguyen.validator.annotation.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 12/01/2018
 * Purpose:
 */
@Component("commonValidator")
public class CommonValidator {
    @Autowired
    protected StringUtil strUtil;

    @Autowired
    protected CheckUtil checkUtil;

    public void validateWithAnnotation(Field field, String fieldVal,
            List<String> errorItemNameList) throws Exception {
        String val = fieldVal == null ? fieldVal : fieldVal.trim();

        // Check require
        if (field.isAnnotationPresent(Required.class)
                && strUtil.isNull(fieldVal)) {
            InputCheckException ex = new InputCheckException(
                    Constants.MSG_REQUIRED_INPUT_ERR,
                    new String[] { field.getAnnotation(Required.class)
                            .displayFieldName() });
            ex.setFieldName(field.getName());
            errorItemNameList.add(field.getName());
            throw ex;
        }

        // Check length
        if (field.isAnnotationPresent(MaxLength.class)) {
            MaxLength anno = field.getAnnotation(MaxLength.class);
            if (!strUtil.isNull(val) && val.length() > anno.maxlength()) {
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_LENGTH_INPUT_ERR,
                        new String[] { anno.displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }

        // Check date format
        // Format validate
        if (field.isAnnotationPresent(Format.class)) {
            Format anno = field.getAnnotation(Format.class);

            // Check date format
            if (anno.type().equals(Format.FormatType.DATE)) {
                boolean isValidDate = checkUtil.isDateFormat(fieldVal,
                        anno.pattern());
                if (!isValidDate) {
                    InputCheckException ex = new InputCheckException(
                            Constants.MSG_DATE_FORMAT_INPUT_ERR, new String[] {
                                    anno.displayFieldName(), anno.pattern() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            }
            else  if (anno.type().equals(Format.FormatType.EMAIL)) {
                boolean isValidEmail = checkUtil.isValidEmailAddress(fieldVal);
                if (!isValidEmail) {
                    InputCheckException ex = new InputCheckException(
                            Constants.MSG_EMAIL_FORMAT_INPUT_ERR, new String[] {
                            anno.displayFieldName(), anno.pattern() });
                    ex.setFieldName(field.getName());
                    errorItemNameList.add(field.getName());
                    throw ex;
                }
            }
        }

        //Check if fieldVal is integer
        if (field.isAnnotationPresent(Number.class)
                && !checkUtil.isSignNumber(fieldVal)) {
            InputCheckException ex = new InputCheckException(
                    Constants.MSG_NUMBER_FORMAT_INPUT_ERR,
                    new String[] { field.getAnnotation(Number.class)
                            .displayFieldName() });
            ex.setFieldName(field.getName());
            errorItemNameList.add(field.getName());
            throw ex;
        }

        //check if integer too small or too big
        if (field.isAnnotationPresent(Number.class)){
            Number anno = field.getAnnotation(Number.class);
            int curValue = Integer.parseInt(fieldVal);
            if (curValue < anno.minValue()){
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_NUMBER_TOO_SMALL_ERR,
                        new String[] { field.getAnnotation(Number.class)
                                .displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
            else if (curValue > anno.maxValue()){
                InputCheckException ex = new InputCheckException(
                        Constants.MSG_NUMBER_TOO_BIG_ERR,
                        new String[] { field.getAnnotation(Number.class)
                                .displayFieldName() });
                ex.setFieldName(field.getName());
                errorItemNameList.add(field.getName());
                throw ex;
            }
        }
    }

}

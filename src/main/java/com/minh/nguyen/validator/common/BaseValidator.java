package com.minh.nguyen.validator.common;

import com.minh.nguyen.exception.BaseException;
import com.minh.nguyen.form.BaseForm;
import com.minh.nguyen.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 12/01/2018
 * Purpose:
 */
public abstract class BaseValidator {

    private static final String HAS_ERROR = "] has error: ";

    private static final String FIELD_STRING_END = "] is ignore validate.";

    private static final String FIELD_STRING_BEGIN = "Field [";

    protected static Logger logger = LoggerFactory.getLogger(BaseValidator.class);


    @Autowired
    protected CommonValidator commonValidator;

    protected ModelMapper modelMapper = new ModelMapper();
    private List<String> errorItemNameList;

    public abstract void validateField(String fieldName, String fieldValue,
            BindingResult errors);

    public abstract void validateLogic(BaseForm clazz, BindingResult errors);


    public void validate(Object targetObj, BindingResult errors,
            String... items) {

        String property = "";
        String fieldVal = "";
        Class<? extends Object> clazz = targetObj.getClass();

        List<Field> fields = new ArrayList<>();
        while (clazz != BaseForm.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        BaseForm baseForm = BaseForm.class.cast(targetObj);
        errorItemNameList = new ArrayList<String>();

        for (Field field : fields) {

            property = field.getName();

            // Check property List type
            if ("java.util.List".equals(field.getType().getName())) {

                List<?> objVal = new ArrayList<Object>();
                try {
                    // set value of property List
                    Method method = targetObj.getClass()
                            .getMethod("get"
                                    + property.substring(0, 1).toUpperCase()
                                    + property.substring(1), new Class[] {});
                    objVal = (List<?>) method.invoke(targetObj,
                            new Object[] {});
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn(FIELD_STRING_BEGIN + property + "] no getter.");
                }

                if (objVal != null) {
                    for (Object obj : objVal) {
                        if (!(obj == null || obj instanceof String
                                || obj instanceof Number)) {
                            validate(obj, errors, property);
                        } else {
                            validateItem(field, (String) obj, property, errors);
                        }
                    }
                }
            } else {

                try {
                    field.setAccessible(true);
                    fieldVal = StringUtil.convertNull(field.get(targetObj));
                } catch (Exception e) {
                    logger.debug("Can not to get data for Field [" + property
                            + "].");
                    e.printStackTrace();
                }

                validateItem(field, fieldVal, property, errors);
            }

        }

        // Validate logic
        if (!errors.hasErrors()) {
            validateLogic(baseForm, errors);
        }
    }

    private void validateItem(Field field, String fieldVal, String property,
            BindingResult errors) {

        try {

            // Validate with annotation
            // TODO : validate annotation by order ?
            commonValidator.validateWithAnnotation(field, fieldVal,
                    errorItemNameList);

            if (!StringUtil.isNull(fieldVal)) {
                validateField(property, fieldVal, errors);
            }
        } catch (Throwable ex) {
            if (ex instanceof BaseException) {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR
                        + ((BaseException) ex).getMessageId());
            } else {
                logger.debug(FIELD_STRING_BEGIN + property + HAS_ERROR
                        + ex.getMessage());
            }
            errors.addError(ex);
        }
    }

    public List<String> getErrorItemNameList() {
        return errorItemNameList;
    }


    public void setErrorItemNameList(List<String> errorItemNameList) {
        this.errorItemNameList = errorItemNameList;
    }


}

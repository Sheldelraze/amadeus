package com.minh.nguyen.controller.common;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.exception.BaseException;
import com.minh.nguyen.util.CheckUtil;
import com.minh.nguyen.util.StringUtil;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 11/01/2018
 * Purpose:
 */
public class BaseController {

    private static final String SCREEN_MESSAGE = "screenMessage";

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    private BaseValidator validator;

    @Autowired
    private ApplicationContext context;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected StringUtil strUtil;

    @Autowired
    protected CheckUtil checkUtil;

    @Autowired
    protected BindingResult bindingResult;

    protected ModelMapper modelMapper;

    protected List<String> exclusiveUpdateField;

    public BaseController() {
    }

    @PostConstruct
    private void init() {
        invokeValidator();
        modelMapper = new ModelMapper();
        exclusiveUpdateField = new ArrayList<>();
        exclusiveUpdateField.add("createClass");
        exclusiveUpdateField.add("createUser");
        exclusiveUpdateField.add("createTime");
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> pContext) {
                if (null == pContext.getSource() || null == pContext.getDestinationType()) {
                    return false;
                }
                if (pContext.getSourceType().equals(String.class)) {
                    if (Constants.BLANK.equals(pContext.getSource().toString())) {
                        return false;
                    }
                }
                if (pContext.getSourceType().equals(String.class)
                        && (Integer.class.equals(pContext.getDestinationType())
                        || int.class.equals(pContext.getDestinationType()))) {
                    if (checkUtil.isInteger(pContext.getSource().toString())) {
                        return true;
                    }
                    return false;
                }
                return true;
            }

        });
    }

    private void invokeValidator() {
        String validateName = this.getClass().getSimpleName();
        validateName = validateName.substring(0, 1).toLowerCase()
                + validateName.substring(1, validateName.indexOf("Controller"))
                + "Validator";

        try {
            // get validator bean object
            validator = (BaseValidator) context.getBean(validateName);
        } catch (Exception e) {
            logger.warn("No customize validator. Use simpleValidator");
            validator = (BaseValidator) context.getBean("simpleValidator");
        }
    }

    protected void validate(Object targetObj,
                            org.springframework.validation.BindingResult result) {

        if (validator != null) {

            bindingResult.clearErrors();
            validator.validate(targetObj, bindingResult);
            if (bindingResult.hasErrors()) {
                try {

                    // insert error to bindingresult
                    if (bindingResult.hasErrors()) {
                        Iterator<Throwable> ite = bindingResult.getAllErrors()
                                .iterator();
                        BaseException ex = null;
                        result.rejectValue(SCREEN_MESSAGE,
                                Constants.MSG_TOTAL_ERR,
                                new Integer[]{

                                        bindingResult.getAllErrors().size()},
                                "Screen error!");
                        while (ite.hasNext()) {
                            ex = (BaseException) ite.next();
                            result.rejectValue(ex.getFieldName(),
                                    ex.getMessageId(), "Error!");
                        }

                    }

                } catch (Exception e) {
                    logger.warn("Input form not existed errors field");
                }
            }
        }

    }


    public void addLogicError(
            org.springframework.validation.BindingResult result,
            String message) {
        result.rejectValue(SCREEN_MESSAGE, message);
    }

    public void addLogicError(
            org.springframework.validation.BindingResult result,
            String messageId, Object[] paramMsg) {
        result.rejectValue(SCREEN_MESSAGE, messageId, paramMsg, "errorLogic");

    }

}

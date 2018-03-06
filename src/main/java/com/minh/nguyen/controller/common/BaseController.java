package com.minh.nguyen.controller.common;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.exception.BaseException;
import com.minh.nguyen.util.CheckUtil;
import com.minh.nguyen.validator.common.BaseValidator;
import com.minh.nguyen.validator.common.BindingResult;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 11/01/2018
 * Purpose: Base class for all controller
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
    protected BindingResult bindingResult;

    @Autowired
    private HttpSession httpSession;

    protected ModelMapper modelMapper;

    public BaseController() {
    }

    @PostConstruct
    private void init() {
        //init validator
        invokeValidator();

        //init modelmapper setting
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Converter<String, Date> toStringDate = new AbstractConverter<String, Date>() {
            @Override
            protected Date convert(String source) {
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                Date date = new Date();
                try {
                    date = formatter.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        };

        modelMapper.addConverter(toStringDate);

        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> pContext) {
                if (null == pContext.getSource() || null == pContext.getDestinationType()) {
                    return false;
                }

                if (pContext.getSourceType().equals(String.class)) {
                    if ("".equals(pContext.getSource().toString())) {
                        return false;
                    }
                    if (pContext.getDestinationType().equals(Date.class)) {
                        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        try {
                            formatter.parse(pContext.getSource().toString());
                            return true;
                        } catch (ParseException e) {
                            logger.error(e.toString());
                            return false;
                        }
                    }
                }
                if (pContext.getSourceType().equals(String.class)
                        && (pContext.getDestinationType().equals(Integer.class))
                        || int.class.equals(pContext.getDestinationType())) {
                    return CheckUtil.isInteger(pContext.getSource().toString());
                }
                return true;
            }

        });
    }

    //init validator base on controller name (ex. ContestController -> ContestValidator)
    private void invokeValidator() {
        String validateName = this.getClass().getSimpleName();
        validateName = validateName.substring(0, validateName.indexOf("Controller"))
                + "Validator";

        try {
            // get validator bean object
            validator = (BaseValidator) context.getBean(validateName);
        } catch (Exception e) {
            logger.warn("No customize validator. Use SimpleValidator");
            validator = (BaseValidator) context.getBean("SimpleValidator");
        }
    }

    //do some magic validation here, better not change anything here...
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
                            if (ex.getParam() == null){
                                result.rejectValue(ex.getFieldName(),
                                        ex.getMessageId(), "Error!");
                            }
                            else{
                                result.rejectValue(ex.getFieldName(),
                                        ex.getMessageId(),ex.getParam(), "Error!");
                            }
                        }

                    }

                } catch (Exception e) {
                    logger.warn("Input form not existed errors field");
                }
            }
        }

    }

    /**
     * this will add message to 'screenMessage' which you will find in many view in form
     * of th:error="*{screenMessage}, the message here will be the ID (not the message itself)
     * these message and ID can be found in message.properties
     */
    public void addLogicError(
            org.springframework.validation.BindingResult result,
            String message) {
        result.rejectValue(SCREEN_MESSAGE, message);
    }

    /**
     * likewise but for message which require parameter
     * ex: msg00x = You have {0} errors.
     * so if we enter parameters:
     * + messageId = msg00x
     * + paramMsg = new Integer[]{3}
     * this will output -> You have 3 errors.
     */
    public void addLogicError(
            org.springframework.validation.BindingResult result,
            String messageId, Object[] paramMsg) {
        result.rejectValue(SCREEN_MESSAGE, messageId, paramMsg, "errorLogic");

    }

    protected ModelAndView createGeneralModel() {
        ModelAndView modelAndView = new ModelAndView();
        boolean canCreateProblem = false;
        boolean canCreateContest = false;
        Object currentUser = httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        if (null != httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES)) {
            List<Integer> defaultAuth = (List<Integer>) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_DEFAULT_AUTHORITIES);
            if (defaultAuth.contains(Constants.AUTH_CREATE_PROBLEM_ID)) {
                canCreateProblem = true;
            }
            if (defaultAuth.contains(Constants.AUTH_CREATE_CONTEST_ID)) {
                canCreateContest = true;
            }
        }
        modelAndView.addObject("currentUser", currentUser);
        modelAndView.addObject("canCreateProblem", canCreateProblem);
        modelAndView.addObject("canCreateContest", canCreateContest);
        return modelAndView;
    }
}

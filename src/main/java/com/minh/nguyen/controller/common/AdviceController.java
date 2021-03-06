package com.minh.nguyen.controller.common;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.exception.UserTryingToBeSmartException;
import com.minh.nguyen.util.ExceptionUtil;
import com.minh.nguyen.util.MessageUtil;
import com.minh.nguyen.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Mr.Minh
 * @since 06/02/2018
 * Purpose: handle every error (exception) occurs in the system, add more if you feel necessary
 */
@Component("AdviceController")
@ControllerAdvice
public class AdviceController {
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private MessageUtil messageUtil;

    //404: page not found
    @ExceptionHandler({NoSuchPageException.class, MethodArgumentTypeMismatchException.class, NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ModelAndView pageNotFoundHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("share/404");
        logger.warn("404 not found!");
        logger.warn("User: " + req.getRemoteUser());
        logger.warn("Request: " + req.getRequestURL());
        return mav;
    }

    //403: access denied
    @ExceptionHandler({AccessDeniedException.class})
    public ModelAndView accessDeniedHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        logger.warn("Access denied!");
        logger.warn("User: " + req.getRemoteUser());
        logger.warn("Request: " + req.getRequestURL());
        mav.setViewName("share/403");
        return mav;
    }

    //500: When some people want to be funny...
    @ExceptionHandler(UserTryingToBeSmartException.class)
    public ModelAndView trollingExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        String handle = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_HANDLE);
        String name = (String) httpSession.getAttribute(Constants.CURRENT_LOGIN_USER_FULLNAME);
        logger.error("User: " + handle + " a.k.a " + name + " was trying to be fancy!");
        logger.error("Request: " + req.getRequestURL());
        logger.error("Error: " + ExceptionUtil.toString(e));
        String url = req.getHeader("referer");
        mav.addObject("previous", url);
        mav.setViewName("share/500");
        mav.addObject("error", e.getMessage());
        return mav;
    }

    //500: rollback exception (usually system error)
    @ExceptionHandler(RollbackException.class)
    public ModelAndView rollbackExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        String errCode = e.toString();
        logger.error("Request: " + req.getRequestURL());
        logger.error("warn: " + messageUtil.getMessage(errCode));
        String url = req.getHeader("referer");
        if (StringUtil.isNull(url)) {
            url = "";
        }
        mav.addObject("previous", url);
        mav.setViewName("share/500");
        mav.addObject("error", messageUtil.getMessage(errCode));
        return mav;
    }

    //500: all other errors and exceptions
    @ExceptionHandler(Exception.class)
    public ModelAndView globalErrorHandle(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        String err = e.toString();
        if (null == err || StringUtil.isBlank(err)) {
                err = ExceptionUtil.toString(e);
        }
        logger.error("Request: " + req.getRequestURL());
        logger.error("warn: " + ExceptionUtil.toString(e));
        String url = req.getHeader("referer");
        if (StringUtil.isNull(url)) {
            url = "";
        }
        mav.addObject("previous", url);
        mav.setViewName("share/500");
        mav.addObject("error", err);
        return mav;
    }
}

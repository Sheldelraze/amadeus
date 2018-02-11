package com.minh.nguyen.controller.common;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mr.Minh
 * @since 06/02/2018
 * Purpose:
 */

@ControllerAdvice
public class AdviceController {
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler({NoSuchPageException.class,NoHandlerFoundException.class,HttpRequestMethodNotSupportedException.class})
    public ModelAndView pageNotFoundHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("share/404");
        logger.warn("404 not found!");
        logger.warn("User: " + req.getRemoteUser());
        logger.warn("Request: " + req.getRequestURL());
        return mav;
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ModelAndView accessDeniedHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        logger.warn("Access denied!");
        logger.warn("User: " + req.getRemoteUser());
        logger.warn("Request: " + req.getRequestURL());
        mav.setViewName("share/403");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView globalErrorHandle(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        if (null == e.toString() || Constants.BLANK.equals(e.toString())){
            mav.addObject("warn", ExceptionUtil.getMessage(e));
        }
        else {
            mav.addObject("warn", e.toString());
        }
        logger.warn("Request: " + req.getRequestURL());
        logger.warn("warn: " + ExceptionUtil.toString(e));
        mav.setViewName("share/500");
        return mav;
    }
}

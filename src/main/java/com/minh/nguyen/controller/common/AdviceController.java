package com.minh.nguyen.controller.common;

import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.util.ExceptionUtil;
import com.minh.nguyen.util.StringUtil;
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
 * Purpose: handle every error occurs in the system, add more if you feel necessary
 */

@ControllerAdvice
public class AdviceController {
    private static final Logger logger = LoggerFactory.getLogger(AdviceController.class);

    //404: page not found
    @ExceptionHandler({NoSuchPageException.class, NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
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

    //500: other errors and exceptions
    @ExceptionHandler(Exception.class)
    public ModelAndView globalErrorHandle(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        String err = e.toString();
        if (null == err || StringUtil.checkBlank(err)) {
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

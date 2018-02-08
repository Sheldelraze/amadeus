package com.minh.nguyen.controller.common;

import com.minh.nguyen.exception.NoSuchPageException;
import com.minh.nguyen.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
        return mav;
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ModelAndView accessDeniedHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("share/403");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView globalErrorHandle(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", e.toString());
        logger.error("Request: " + req.getRequestURL());
        logger.error("Error: " + ExceptionUtil.toString(e));
        mav.setViewName("share/500");
        return mav;
    }
}

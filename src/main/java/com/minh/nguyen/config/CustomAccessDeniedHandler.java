package com.minh.nguyen.config;

import com.minh.nguyen.exception.NoSuchPageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// handle 403 page
/**
 * WARNING: THIS CLASS IS NOW DEPRECATED. ALL HANDLER NOW MOVE TO CLASS AdviceController
 * FIXING ANYTHING HERE WONT CHANGE ANYTHING. HOWEVER I WAS NOT SURE IF DELETE THIS CLASS WILL
 * BROKE SOMETHING SO JUST LEAVE THIS HERE.
 */
@Component("CustomAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {

        //handle access denied here
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.warn("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI());
        }


        if (e instanceof NoSuchPageException){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/404");
        }
        else{
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403");
        }
    }
}
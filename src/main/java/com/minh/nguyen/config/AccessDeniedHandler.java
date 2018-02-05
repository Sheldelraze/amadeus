package com.minh.nguyen.config;

import com.minh.nguyen.exception.NoSuchPageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// handle 403 page
@Component("AccessDeniedHandler")
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    private static Logger logger = LoggerFactory.getLogger(AccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException {

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
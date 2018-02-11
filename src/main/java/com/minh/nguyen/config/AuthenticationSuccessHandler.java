package com.minh.nguyen.config;

import com.minh.nguyen.constants.Constants;
import com.minh.nguyen.entity.UserEntity;
import com.minh.nguyen.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mr.Minh
 * @since 11/02/2018
 * Purpose:
 */
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public final Integer SESSION_TIMEOUT_IN_SECONDS = 30;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS);
        super.onAuthenticationSuccess(request,response,authentication);
    }
}

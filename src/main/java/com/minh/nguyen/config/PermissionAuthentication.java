package com.minh.nguyen.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Minh
 * @since 04/02/2018
 * Purpose:
 */
@Component("PermissionAuthentication")
public class PermissionAuthentication {
    public boolean checkRole(Authentication authentication, String role) {
        return true;
    }
    public boolean checkPermission(Authentication authentication, String role) {
        return false;
    }
}

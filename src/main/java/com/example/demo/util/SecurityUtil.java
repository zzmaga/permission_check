package com.example.demo.util;

import com.example.demo.exception.auth.UnauthorizedException;
import com.example.demo.filter.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class SecurityUtil {
    public static UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new UnauthorizedException();
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetails user) {
            return user.getUserId();
        }
        if (principal instanceof String s) {
            if ("anonymousUser".equals(s)) {
                throw new UnauthorizedException();
            }
            try {
                return UUID.fromString(s);
            } catch (IllegalArgumentException e) {
                throw new UnauthorizedException();
            }
        }
        throw new UnauthorizedException();
    }

    public static String getCurrentJwtToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials();
        }

        return null;
    }
}
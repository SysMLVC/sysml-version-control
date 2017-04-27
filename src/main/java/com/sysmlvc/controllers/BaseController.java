package com.sysmlvc.controllers;

/**
 * Created by huijun on 10/14/16.
 */

import java.security.SecureRandom;

import org.springframework.security.core.context.SecurityContextHolder;

import com.sysmlvc.domains.nodes.User;
import com.sysmlvc.security.UserDetailsImpl;

public class BaseController {

    static private final String alphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static boolean isAuthorized(User user) {
        String auth = getCurrentUser();

        return (auth.equalsIgnoreCase(user.getUsername()) || isAdmin());
    }

    static boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equalsIgnoreCase("ROLE_ADMIN");
    }

    static String getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser().getUsername();
    }

    static String generateActivation() {
        int len = 15;
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(alphaNumeric.charAt(rnd.nextInt(alphaNumeric.length())));
        }
        return sb.toString();
    }
}

package com.sysmlvc.utils;

/**
 * Created by Jason Han on 11/5/16.
 */

import java.util.HashMap;
import java.util.Map;

class EmailTemplates {

    private static final String emailFromName = "SYSMLVC";

    private static final String activateAccountFromAddress = "website@sysmlvc.com";
    private static final String activateAccountSubject = "Verify Your Email with SYSMLVC";

    private static final String forgotPasswordFromAddress = "no-reply@sysmlvc.com>";
    private static final String forgotPasswordSubject = "Forgot your SYSMLVC password?";

    private static final String activateAccountHTML = "<p> Thank you for registering with GIBLIB - you're one click away from becoming a GIBLIBer! </p>\n" +
            "\n" +
            "<p> Verify your email by clicking the link below: </p>\n" +
            "\n" +
            "<p>\n" +
            "    <strong>IMPORTANT: After clicking the link below, you will need to retype your email and password to complete the registration process.</strong>\n" +
            "</p>\n" +
            "\n" +
            "<p>\n" +
            "    <a href=\"{{link}}\" target=\"_blank\" style=\"font-size: 16px; font-family: Open Sans, sans-serif; color: #ffffff; text-decoration: none; border-radius: 3px; -webkit-border-radius: 3px; -moz-border-radius: 3px; background-color: #EB7035; border-top: 12px solid #EB7035; border-bottom: 12px solid #EB7035; border-right: 18px solid #EB7035; border-left: 18px solid #EB7035; display: inline-block;\">Confirm My Email</a>\n" +
            "</p>\n" +
            "\n" +
            "\n" +
            "<p> Sincerely, </p>\n" +
            "<p> The GIBLIB team </p>";

    private static final String activateAccountText = "Thank you for registering with GIBLIB - you're one click away from becoming a GIBLIBer!\n" +
            "\n" +
            "Verify your email by clicking: {{link}}\n" +
            "\n" +
            "IMPORTANT: After clicking the link below, you will need to retype your email and password to complete the registration process.\n" +
            "\n" +
            "Sincerely,\n" +
            "The GIBLIB team";

    private static final String forgotPasswordHTML = "<p>We've received a request to reset your password. If you didn't make the request, just ignore this email. Otherwise, you can reset your password using this link: </p>\n" +
            "\n" +
            "<p>\n" +
            "    <a href=\"{{link}}\" target=\"_blank\" style=\"font-size: 16px; font-family: Open Sans, sans-serif; color: #ffffff; text-decoration: none; border-radius: 3px; -webkit-border-radius: 3px; -moz-border-radius: 3px; background-color: #EB7035; border-top: 12px solid #EB7035; border-bottom: 12px solid #EB7035; border-right: 18px solid #EB7035; border-left: 18px solid #EB7035; display: inline-block;\">Reset My Password</a>\n" +
            "</p>\n" +
            "\n" +
            "<p> Sincerely, </p>\n" +
            "<p> The GIBLIB team </p>";

    private static final String forgotPasswordText = "We've received a request to reset your password. If you didn't make the request, just ignore this email. Otherwise, you can reset your password using this link: {{link}}\n" +
            "\n" +
            "Sincerely,\n" +
            "The GIBLIB team";

    static Map<String, String> getTemplate(String name) {
        Map<String, String> result = new HashMap<>();
        result.put("fromName", emailFromName);
        switch (name) {
            case "activate":
                result.put("fromAddress", activateAccountFromAddress);
                result.put("subject", activateAccountSubject);
                result.put("html", activateAccountHTML);
                result.put("text", activateAccountText);
            case "forgot":
                result.put("fromAddress", forgotPasswordFromAddress);
                result.put("subject", forgotPasswordSubject);
                result.put("html", forgotPasswordHTML);
                result.put("text", forgotPasswordText);
        }

        return result;
    }
}

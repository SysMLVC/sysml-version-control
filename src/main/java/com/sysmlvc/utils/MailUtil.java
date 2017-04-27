package com.sysmlvc.utils;

/**
 * Created by huijun on 10/28/16.
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    public void sendEmail(String templateName, String toAddress, Map<String, String> replacements) {

        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Map<String, String> template = EmailTemplates.getTemplate(templateName);

            MimeMessage message = new MimeMessage(session);
            message.setHeader("X-MC-Tags", templateName);
            message.setFrom(new InternetAddress(template.get("fromAddress"), template.get("fromName")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(template.get("subject"));
            message.setContent(formatEmail(template.get("html"), replacements), "text/html");
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatEmail(String html, Map<String, String> args) {
        args.forEach((key, value) -> {
            html.replace("{{" + key + "}}", value);
        });
        return html;
    }
}

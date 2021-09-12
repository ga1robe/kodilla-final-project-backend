package com.crud.finalbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalTime;

@Service
public class EmailCreatorService {
    public static final String MAIL_TYPE_USER_CUSTOM_NOTIFICATIONS = "CUSTOM_NOTIFICATIONS";

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private boolean isAfterNoon() {
        return LocalTime.now().isAfter(LocalTime.NOON);
    }

    private String buildUserCustomNotificationEmail(String message) {

        Context context = new Context();
        context.setVariable("message", message);
//        context.setVariable("portal_url", "http://localhost:8080/seasonaltrails/preferences"); //TODO. checking and change.
        context.setVariable("portal_url", "http://localhost:8080/seasonaltrails/users"); //TODO. checking and change.
        context.setVariable("button", "Visit website");
        context.setVariable("goodbye_message", "Have a nice day");
        context.setVariable("show_button", true);
        context.setVariable("is_after_12", isAfterNoon());
        context.setVariable("service_details", "Service: seasonal hiking trails");
        context.setVariable("customer_name", "TEST NAME HERE");

        return templateEngine.process("mail/custom-user-preferences-mail", context);
    }


    public String createMail(final String mailType, final String message) {
        switch (mailType) {
            case MAIL_TYPE_USER_CUSTOM_NOTIFICATIONS:
                return buildUserCustomNotificationEmail(message);
            default:
                return null;
        }
    }
}

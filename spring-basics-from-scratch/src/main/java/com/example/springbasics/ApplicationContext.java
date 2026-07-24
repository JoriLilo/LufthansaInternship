package com.example.springbasics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ApplicationContext {
    private final NotificationService notificationService;

    public ApplicationContext(@Qualifier("consoleNotificationService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyUser(String message) {

        notificationService.send(message);
    }
}

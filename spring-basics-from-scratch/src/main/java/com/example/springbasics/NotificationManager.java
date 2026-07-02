package com.example.springbasics;

import org.springframework.stereotype.Component;

@Component
public class NotificationManager {

    private final NotificationService notificationService;


    public NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyUser(String message){

        notificationService.send("Sending EMAIL notification: "+ message);
    }

}

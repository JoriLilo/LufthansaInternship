package com.example.springbasics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service

public class EmailNotificationsService implements NotificationService{

    @Override

    @Value("${notification.prefix}")
    public void send(String message) {
        System.out.println(message);

    }
}

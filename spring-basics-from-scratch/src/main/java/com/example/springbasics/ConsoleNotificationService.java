package com.example.springbasics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class ConsoleNotificationService implements NotificationService {

    @Override
    @Value("${notification.prefix}")
    public void send(String message) {
        System.out.println("Sending CONSOLE notification: " + message);
    }
}

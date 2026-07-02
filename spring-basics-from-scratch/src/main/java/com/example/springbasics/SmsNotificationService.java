package com.example.springbasics;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SmsNotificationService implements NotificationService {


    @Override
    public void send(String message) {
        System.out.println("Sending sms: " + message);
    }
}

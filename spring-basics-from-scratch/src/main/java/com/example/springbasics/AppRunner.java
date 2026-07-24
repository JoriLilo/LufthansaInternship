package com.example.springbasics;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private NotificationManager notificationManager;
    private ApplicationContext applicationContext;
    public AppRunner(NotificationManager notificationManager, ApplicationContext applicationContext) {
        this.notificationManager = notificationManager;
        this.applicationContext = applicationContext;
    }

    @Override

    public void run(String... args) throws Exception {
        notificationManager.notifyUser("Welcome to Spring!");

        System.out.println("Instance 1: " + notificationManager);
        System.out.println("Instance 2: " + applicationContext);

    }

}

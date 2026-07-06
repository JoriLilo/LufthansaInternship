package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service

public class GreetingService  {

    @Value("${app.greeting}")
    private String greeting;


    public String getGreeting(String name) {
        return " Hello  from "+ greeting +" "+ name + "!";
    }

}

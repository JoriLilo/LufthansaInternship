package com.example.springbasics;

import org.springframework.stereotype.Service;

@Service
public class AuditService {

    String message;

    public void setMessage(String message) {
        this.message = message;

    }
    public void getMessage() {
        System.out.println(message);
    }



}

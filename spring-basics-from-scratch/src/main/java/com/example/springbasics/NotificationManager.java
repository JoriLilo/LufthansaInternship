package com.example.springbasics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {

    private final NotificationService notificationService;

    private AuditService auditService=new AuditService();

    public NotificationManager(@Qualifier("consoleNotificationService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    public void notifyUser(String message){

        notificationService.send(message);
        auditService.setMessage("AUDIT: Notification was sent");
        setAuditService(auditService);

        auditService.getMessage();

    }

}

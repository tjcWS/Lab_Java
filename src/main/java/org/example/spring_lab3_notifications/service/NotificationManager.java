package org.example.spring_lab3_notifications.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class NotificationManager {
    private final Map<String, MessageService> messageServices;

    public NotificationManager(Map<String, MessageService> messageServices) {
        this.messageServices = messageServices;
    }

    public void notify(String message, String recipient, String serviceType) {
        MessageService service = messageServices.get(serviceType);
        if (service != null) {
            service.sendMessage(message, recipient);
        } else {
            System.out.println("Service type '" + serviceType + "' not found");
        }
    }
}
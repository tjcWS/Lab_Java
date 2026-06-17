package org.example.spring_lab3_notifications.service;

import lombok.RequiredArgsConstructor;
import org.example.spring_lab3_notifications.model.dto.NotificationDto;
import org.example.spring_lab3_notifications.model.entity.Notification;
import org.example.spring_lab3_notifications.model.entity.User;
import org.example.spring_lab3_notifications.model.enums.NotificationChannel;
import org.example.spring_lab3_notifications.model.enums.NotificationStatus;
import org.example.spring_lab3_notifications.repository.NotificationRepository;
import org.example.spring_lab3_notifications.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Notification createNotification(NotificationDto request) {
        User user = userRepository.findById(request.getRecipientId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Notification notification = new Notification();
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());
        notification.setStatus(NotificationStatus.CREATED);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRecipient(user);

        if (request.getStatus() == NotificationStatus.SENT) {
            notification.setSentAt(LocalDateTime.now());
        }

        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Уведомление не найдено"));
    }

    @Transactional
    public Notification updateNotification(Long id, NotificationDto request) {
        Notification notification = getNotificationById(id);
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setChannel(request.getChannel());
        notification.setStatus(request.getStatus());

        // Задание 6
        if (request.getStatus() == NotificationStatus.SENT && notification.getSentAt() == null) {
            notification.setSentAt(LocalDateTime.now());
        }

        return notificationRepository.save(notification);
    }

    @Transactional
    public void deleteNotification(Long id) {
        Notification notification = getNotificationById(id);
        notificationRepository.delete(notification);
    }

    public List<Notification> getNotificationsByStatus(NotificationStatus status) {
        return notificationRepository.findByStatus(status);
    }

    public List<Notification> getNotificationsByChannel(NotificationChannel channel) {
        return notificationRepository.findByChannel(channel);
    }

    public List<Notification> getNotificationsByRecipientId(Long recipientId) {
        return notificationRepository.findByRecipientId(recipientId);
    }

    // Задание 3
    public List<Notification> getByStatusAndChannel(NotificationStatus status, NotificationChannel channel) {
        return notificationRepository.findByStatusAndChannel(status, channel);
    }

    // Задание 4
    public List<Notification> getByStatusOrderByCreatedAtDesc(NotificationStatus status) {
        return notificationRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    // Задание 5
    public List<Notification> getByRecipientIdAndStatus(Long recipientId, NotificationStatus status) {
        return notificationRepository.findByRecipientIdAndStatus(recipientId, status);
    }
}
package org.example.spring_lab3_notifications.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_lab3_notifications.mapper.NotificationMapper;
import org.example.spring_lab3_notifications.model.dto.NotificationDto;
import org.example.spring_lab3_notifications.model.enums.NotificationChannel;
import org.example.spring_lab3_notifications.model.enums.NotificationStatus;
import org.example.spring_lab3_notifications.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @PostMapping("/add")
    public NotificationDto createNotification(@RequestBody @Valid NotificationDto request) {
        return notificationMapper.toDto(notificationService.createNotification(request));
    }

    @GetMapping("/all")
    public List<NotificationDto> getAllNotifications() {
        return notificationMapper.toDtoList(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public NotificationDto getNotificationById(@PathVariable Long id) {
        return notificationMapper.toDto(notificationService.getNotificationById(id));
    }

    @PutMapping("/{id}")
    public NotificationDto updateNotification(@PathVariable Long id, @RequestBody @Valid NotificationDto request) {
        return notificationMapper.toDto(notificationService.updateNotification(id, request));
    }

    @DeleteMapping("/{id}")
    public String deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return "Уведомление удалено";
    }

    @GetMapping("/status/{status}")
    public List<NotificationDto> getByStatus(@PathVariable NotificationStatus status) {
        return notificationMapper.toDtoList(notificationService.getNotificationsByStatus(status));
    }

    @GetMapping("/channel/{channel}")
    public List<NotificationDto> getByChannel(@PathVariable NotificationChannel channel) {
        return notificationMapper.toDtoList(notificationService.getNotificationsByChannel(channel));
    }

    @GetMapping("/recipient/{recipientId}")
    public List<NotificationDto> getByRecipientId(@PathVariable Long recipientId) {
        return notificationMapper.toDtoList(notificationService.getNotificationsByRecipientId(recipientId));
    }

    @GetMapping("/status/{status}/channel/{channel}")
    public List<NotificationDto> getByStatusAndChannel(@PathVariable NotificationStatus status,
                                                       @PathVariable NotificationChannel channel) {
        return notificationMapper.toDtoList(notificationService.getByStatusAndChannel(status, channel));
    }

    @GetMapping("/sorted/status/{status}/desc")
    public List<NotificationDto> getByStatusSortedDesc(@PathVariable NotificationStatus status) {
        return notificationMapper.toDtoList(notificationService.getByStatusOrderByCreatedAtDesc(status));
    }

    @GetMapping("/recipient/{recipientId}/status/{status}")
    public List<NotificationDto> getByRecipientIdAndStatus(@PathVariable Long recipientId,
                                                           @PathVariable NotificationStatus status) {
        return notificationMapper.toDtoList(notificationService.getByRecipientIdAndStatus(recipientId, status));
    }
}
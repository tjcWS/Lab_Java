package org.example.spring_lab3_notifications.service;

import org.example.spring_lab3_notifications.model.dto.NotificationDto;
import org.example.spring_lab3_notifications.model.dto.UserDto;
import org.example.spring_lab3_notifications.model.entity.Notification;
import org.example.spring_lab3_notifications.model.entity.User;
import org.example.spring_lab3_notifications.model.enums.NotificationChannel;
import org.example.spring_lab3_notifications.repository.NotificationRepository;
import org.example.spring_lab3_notifications.repository.UserRepository;
import org.example.spring_lab3_notifications.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldCreateNotification() {
        // Подготовка: создаем пользователя
        User user = new User();
        user.setId(1L);
        user.setEmail("ivan@example.com");
        user.setName("Иван Иванов");

        // Подготовка: DTO для создания уведомления
        NotificationDto dto = NotificationDto.builder()
                .title("Напоминание")
                .message("Завтра дедлайн по Spring Boot")
                .channel(NotificationChannel.EMAIL)
                .recipientId(1L)
                .build();

        // Подготовка: savedNotification — то, что вернет mock-репозиторий
        Notification savedNotification = new Notification();
        savedNotification.setTitle(dto.getTitle());
        savedNotification.setMessage(dto.getMessage());
        savedNotification.setChannel(dto.getChannel());
        savedNotification.setRecipient(user);

        // Настройка mock-объектов
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(notificationRepository.save(any(Notification.class))).thenReturn(savedNotification);

        // Вызов тестируемого метода
        Notification result = notificationService.createNotification(dto);

        // Проверки
        assertNotNull(result);
        assertEquals("Напоминание", result.getTitle());
        assertEquals("Завтра дедлайн по Spring Boot", result.getMessage());
        assertEquals(NotificationChannel.EMAIL, result.getChannel());
        assertEquals(user, result.getRecipient());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Подготовка: DTO с несуществующим ID пользователя
        NotificationDto dto = NotificationDto.builder()
                .title("Напоминание")
                .message("Сообщение")
                .channel(NotificationChannel.EMAIL)
                .recipientId(99L)  // Такого пользователя нет
                .build();

        // Настройка mock: при поиске ID 99 вернуть пустой Optional
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Проверка: метод должен выбросить исключение
        assertThrows(RuntimeException.class,
                () -> notificationService.createNotification(dto));
    }

    @Test
    void shouldGetNotificationById() {
        // Подготовка
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setTitle("Важное уведомление");
        notification.setMessage("Текст сообщения");

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        // Вызов
        Notification result = notificationService.getNotificationById(1L);

        // Проверка
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Важное уведомление", result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenNotificationNotFound() {
        // Подготовка: при поиске ID 999 возвращаем пустой Optional
        when(notificationRepository.findById(999L)).thenReturn(Optional.empty());

        // Проверка: должно выброситься исключение
        assertThrows(RuntimeException.class,
                () -> notificationService.getNotificationById(999L));
    }

    @Test
    void shouldVerifyExactlyOneSaveCall() {
        // Подготовка
        UserDto dto = UserDto.builder()
                .name("Тестовый пользователь")
                .email("test@example.com")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Вызов
        userService.createUser(dto);

        // Проверка: метод save был вызван ровно 1 раз
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldDemonstrateSpy() {
        // Создаем реальный список и оборачиваем его в spy
        List<String> realList = new ArrayList<>();
        List<String> spyList = spy(realList);

        // Вызываем метод add на spy-объекте
        spyList.add("Spring Boot");

        // Проверяем, что метод add был вызван
        verify(spyList).add("Spring Boot");

        // Реальный метод add тоже сработал, поэтому размер стал 1
        assertEquals(1, spyList.size());
    }
}
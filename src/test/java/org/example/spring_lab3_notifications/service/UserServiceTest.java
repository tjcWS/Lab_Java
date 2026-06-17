package org.example.spring_lab3_notifications.service;

import org.example.spring_lab3_notifications.model.dto.UserDto;
import org.example.spring_lab3_notifications.model.entity.User;
import org.example.spring_lab3_notifications.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser() {
        // Создаем DTO
        UserDto dto = new UserDto();
        dto.setName("Иван Иванов");
        dto.setEmail("ivan@example.com");

        // Создаем пользователя для возврата
        User savedUser = new User();
        savedUser.setName(dto.getName());
        savedUser.setEmail(dto.getEmail());

        // Настраиваем mock
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Вызываем метод
        User result = userService.createUser(dto);

        // Проверяем
        assertNotNull(result);
        assertEquals("Иван Иванов", result.getName());
    }

    @Test
    void testVerifySaveCalled() {
        UserDto dto = new UserDto();
        dto.setName("Петр Петров");

        when(userRepository.save(any(User.class))).thenReturn(new User());

        userService.createUser(dto);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldGetUserById() {
        // Подготовка
        User user = new User();
        user.setId(1L);
        user.setName("Иван Иванов");
        user.setEmail("ivan@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Вызов
        User result = userService.getUserById(1L);

        // Проверка
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Иван Иванов", result.getName());
    }

    @Test
    void shouldDeleteUser() {
        // Вызов
        userService.deleteUser(1L);

        // Проверка: метод deleteById был вызван ровно 1 раз с параметром 1L
        verify(userRepository, times(1)).deleteById(1L);
    }
}
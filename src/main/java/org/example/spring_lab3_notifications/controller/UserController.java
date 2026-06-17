package org.example.spring_lab3_notifications.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_lab3_notifications.model.dto.UserDto;
import org.example.spring_lab3_notifications.model.entity.User;
import org.example.spring_lab3_notifications.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public UserDto createUser(@RequestBody @Valid UserDto request) {
        User response = userService.createUser(request);
        return UserDto.builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .telegramChatId(response.getTelegramChatId())
                .deviceToken(response.getDeviceToken())
                .createdAt(response.getCreatedAt())
                .build();
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .telegramChatId(user.getTelegramChatId())
                        .deviceToken(user.getDeviceToken())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User response = userService.getUserById(id);
        return UserDto.builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .telegramChatId(response.getTelegramChatId())
                .deviceToken(response.getDeviceToken())
                .createdAt(response.getCreatedAt())
                .build();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto request) {
        User response = userService.updateUser(id, request);
        return UserDto.builder()
                .id(response.getId())
                .name(response.getName())
                .email(response.getEmail())
                .phone(response.getPhone())
                .telegramChatId(response.getTelegramChatId())
                .deviceToken(response.getDeviceToken())
                .createdAt(response.getCreatedAt())
                .build();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Пользователь удален";
    }
}
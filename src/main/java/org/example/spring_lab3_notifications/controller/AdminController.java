package org.example.spring_lab3_notifications.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/ping")
    public String ping() {
        return "Доступ разрешен. Вы вошли как администратор!";
    }

    @GetMapping("/info")
    public String adminInfo() {
        return "Это административная панель. Доступно только для ROLE_ADMIN.";
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Дополнительная защита на уровне метода
    public String deleteUser(@PathVariable Long id) {
        return "Пользователь с ID " + id + " удален (демо-режим)";
    }
}

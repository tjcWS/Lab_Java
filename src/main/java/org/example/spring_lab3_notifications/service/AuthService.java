package org.example.spring_lab3_notifications.service;

import lombok.RequiredArgsConstructor;
import org.example.spring_lab3_notifications.model.dto.RegisterRequest;
import org.example.spring_lab3_notifications.model.entity.User;
import org.example.spring_lab3_notifications.model.enums.UserRole;
import org.example.spring_lab3_notifications.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        // Проверка уникальности email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // пароль шифруется перед сохранением
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(UserRole.ROLE_USER);  // Новая регистрация - обычный пользователь
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}
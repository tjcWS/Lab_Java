package org.example.spring_lab3_notifications.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor  // ← Это добавит конструктор без параметров
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String deviceToken;
    private String telegramChatId;
    private String telegramUsername;
    private String role;  // ← Добавь это поле (если его нет)
    private LocalDateTime createdAt;
}
package org.example.spring_lab3_notifications.mapper;

import org.example.spring_lab3_notifications.model.dto.UserDto;
import org.example.spring_lab3_notifications.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setDeviceToken(user.getDeviceToken());
        dto.setTelegramChatId(user.getTelegramChatId());
        dto.setRole(user.getRole().name());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
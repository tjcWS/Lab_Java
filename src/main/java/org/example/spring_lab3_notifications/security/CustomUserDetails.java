package org.example.spring_lab3_notifications.security;

import lombok.AllArgsConstructor;
import org.example.spring_lab3_notifications.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;  // Ссылка на оригинальную сущность

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Преобразуем роль пользователя в объект GrantedAuthority
        // Spring Security использует это для авторизации
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        // Возвращаем зашифрованный пароль из базы данных
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // Используем email как уникальный идентификатор пользователя
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Аккаунт не просрочен
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Аккаунт не заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Учетные данные действительны
    }

    @Override
    public boolean isEnabled() {
        return true;  // Аккаунт активен
    }

    // Дополнительный метод для получения оригинального пользователя
    public User getUser() {
        return user;
    }
}
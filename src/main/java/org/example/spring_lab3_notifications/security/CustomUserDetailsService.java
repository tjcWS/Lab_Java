package org.example.spring_lab3_notifications.security;

import lombok.RequiredArgsConstructor;
import org.example.spring_lab3_notifications.model.entity.User;
import org.example.spring_lab3_notifications.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Поиск пользователя в базе данных по email
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Пользователь с email " + username + " не найден"
                ));

        // Возвращаем адаптер UserDetails
        return new CustomUserDetails(user);
    }
}


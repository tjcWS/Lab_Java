package org.example.spring_lab3_notifications.repository;

import org.example.spring_lab3_notifications.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Поиск пользователя по email - используется для аутентификации
    Optional<User> findByEmail(String email);

    // Проверка существования email - для валидации при регистрации
    boolean existsByEmail(String email);
}


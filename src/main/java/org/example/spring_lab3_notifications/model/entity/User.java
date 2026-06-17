package org.example.spring_lab3_notifications.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_lab3_notifications.model.enums.UserRole;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // НОВОЕ ПОЛЕ: пароль пользователя (будет храниться в зашифрованном виде)
    @Column(nullable = false)
    private String password;

    // НОВОЕ ПОЛЕ: роль пользователя (определяет права доступа)
    @Enumerated(EnumType.STRING)  // Хранит строковое значение, а не порядковый номер
    @Column(nullable = false)
    private UserRole role;

    private String phone;
    private String deviceToken;
    private String telegramChatId;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Notification> notifications = new ArrayList<>();
}
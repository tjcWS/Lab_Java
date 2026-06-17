package org.example.spring_lab3_notifications;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {

    @Test
    void shouldThrowException() {
        // assertThrows проверяет, что код в лямбда-выражении выбрасывает указанное исключение
        assertThrows(ArithmeticException.class, () -> {
            int result = 10 / 0;  // деление на ноль выбросит ArithmeticException
        });
    }
}

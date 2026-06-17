package org.example.spring_lab3_notifications;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    @Test
    void shouldCheckAssertions() {
        String value = "Spring";

        // assertEquals - проверка равенства
        assertEquals("Spring", value);

        // assertNotNull - проверка, что объект не null
        assertNotNull(value);

        // assertTrue - проверка истинности условия
        assertTrue(value.startsWith("Sp"));

        // assertFalse - проверка ложности условия
        assertFalse(value.isEmpty());
    }
}

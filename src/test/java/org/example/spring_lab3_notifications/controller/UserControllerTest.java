package org.example.spring_lab3_notifications.controller;

import org.example.spring_lab3_notifications.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void shouldReturnOkWhenGettingAllUsers() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk());
    }
}

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    void shouldReturnOkWhenGettingNotifications() throws Exception {
        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundForInvalidId() throws Exception {
        when(notificationService.getNotificationById(999L))
                .thenThrow(new RuntimeException("Notification not found"));

        mockMvc.perform(get("/notifications/999"))
                .andExpect(status().isNotFound());
    }
}
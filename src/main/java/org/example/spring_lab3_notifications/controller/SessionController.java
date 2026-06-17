package org.example.spring_lab3_notifications.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/debug")
public class SessionController {

    @GetMapping("/session-info")
    public Map<String, Object> getSessionInfo(HttpSession session) {
        Map<String, Object> info = new HashMap<>();
        info.put("sessionId", session.getId());
        info.put("creationTime", session.getCreationTime());
        info.put("lastAccessedTime", session.getLastAccessedTime());
        info.put("isNew", session.isNew());
        info.put("authenticated", SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            info.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
            info.put("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        }

        return info;
    }
}

package com.example.blogs.notification;

import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;


//    @MessageMapping("/notif")
//    @SendTo("/topic/push")
    @GetMapping
    public List<NotificationResponseDto> getAll(Principal principal) {
        return notificationService.getAllNotifiesByLoggedUser(principal);
    }

}

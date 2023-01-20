package com.example.blogs.notification;

import com.example.blogs.security.SecurityUtil;
import com.example.blogs.security.user.User;
import com.example.blogs.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    public void send(NotificationRequestDto notificationRequestDto) {
        Notification notification = new Notification();
        User user = userRepository.findById(notificationRequestDto.getUserId()).orElseThrow();
        notification.setUser(user);
        notification.setTitle(notificationRequestDto.getTitle());
        notification.setContent(notificationRequestDto.getContent());
        notificationRepository.save(notification);
    }

    public List<NotificationResponseDto> getAllNotifiesByLoggedUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        return notificationRepository.getAllNotifications(user.getId());
    }
}
